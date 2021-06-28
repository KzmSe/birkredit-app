package com.birkredit.service.payment.impl;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.payment.dto.PaymentRequest;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Customer;
import com.birkredit.entity.Payment;
import com.birkredit.exception.DataNotFoundException;
import com.birkredit.exception.response.ResponseMessage;
import com.birkredit.mapper.CustomerMapper;
import com.birkredit.repository.customer.CustomerRepository;
import com.birkredit.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private CustomerRepository customerRepository;

    @Autowired
    public PaymentServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerCreditResponse payByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber, PaymentRequest request) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerNumberAndCredits_CreditNumber(customerNumber, creditNumber);
        optionalCustomer.orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CREDIT_NOT_FOUND_BY_CUSTOMER_NUMBER_OR_CREDIT_NUMBER));

        Customer customer = optionalCustomer.get();
        List<Credit> credits = customer.getCredits();

        Credit credit = credits.stream()
                .filter(c -> c.getCreditNumber().equals(creditNumber))
                .findFirst().orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CREDIT_NOT_FOUND_BY_CUSTOMER_NUMBER_OR_CREDIT_NUMBER));

        updateCreditInfo(credit, request.getAmount());

        Customer updatedCustomer = customerRepository.save(customer);

        return CustomerMapper.INSTANCE.customerToCustomerCreditResponse(updatedCustomer);
    }

    @Override
    public void updateCreditInfo(Credit credit, Double requestedAmount) {
        Double amount = requestedAmount;

        credit.setDebt(credit.getDebt() - amount);

        for (Payment payment : credit.getPayments()) {
            if (!payment.getIsPayed() && amount > 0) {
                if (amount > payment.getInterestAmountOfMonth()) {
                    amount = amount - payment.getInterestAmountOfMonth();
                    payment.setInterestAmountOfMonth(0D);
                } else {
                    payment.setInterestAmountOfMonth(payment.getInterestAmountOfMonth() - amount);
                    amount = 0D;
                }

                if (amount > payment.getMainAmountOfMonth()) {
                    amount = amount - payment.getMainAmountOfMonth();
                    payment.setMainAmountOfMonth(0D);
                } else {
                    payment.setMainAmountOfMonth(payment.getMainAmountOfMonth() - amount);
                    amount = 0D;
                }

                if (payment.getMainAmountOfMonth() == 0) {
                    payment.setIsPayed(true);
                }
            }
        }
    }
}
