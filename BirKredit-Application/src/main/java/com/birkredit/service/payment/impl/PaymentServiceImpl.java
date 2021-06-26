package com.birkredit.service.payment.impl;

import com.birkredit.controller.dto.CustomerCreditResponse;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Customer;
import com.birkredit.entity.Payment;
import com.birkredit.exception.DataNotFoundException;
import com.birkredit.exception.response.ResponseMessage;
import com.birkredit.mapper.CustomerMapper;
import com.birkredit.repository.customer.CustomerJpaRepository;
import com.birkredit.service.payment.PaymentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    public PaymentServiceImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerCreditResponse payByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber, Double amount) {
        Optional<Customer> optionalCustomer = customerJpaRepository.findByCustomerNumberAndCredits_CreditNumber(customerNumber, creditNumber);
        optionalCustomer.orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CREDIT_NOT_FOUND_BY_CUSTOMER_NUMBER_OR_CREDIT_NUMBER));

        Customer customer = optionalCustomer.get();
        List<Credit> credits = customer.getCredits();

        Credit credit = credits.stream()
                .filter(c -> c.getCreditNumber().equals(creditNumber))
                .findFirst().orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CREDIT_NOT_FOUND_BY_CUSTOMER_NUMBER_OR_CREDIT_NUMBER));

        credit.setDebt(credit.getDebt() - amount);

        //update credit
        for (int i = 0; i < credit.getPayments().size(); i++) {
            Payment payment = credit.getPayments().get(i);

            if (!payment.getIsPayed() && amount > 0) {
                payment.setPaymentNumber(RandomStringUtils.random(20, false, true));
                payment.setPaymentDate(LocalDateTime.now());

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

        Customer updatedCustomer = customerJpaRepository.save(customer);

        return CustomerMapper.INSTANCE.customerToCustomerCreditResponse(updatedCustomer);
    }
}
