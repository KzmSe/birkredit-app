package com.birkredit.service.credit.impl;

import com.birkredit.controller.credit.dto.CreditRequest;
import com.birkredit.controller.credit.dto.CreditResponse;
import com.birkredit.controller.customer.dto.CustomerLiteResponse;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Customer;
import com.birkredit.entity.Payment;
import com.birkredit.exception.DataNotFoundException;
import com.birkredit.exception.response.ResponseMessage;
import com.birkredit.mapper.CreditMapper;
import com.birkredit.mapper.CustomerMapper;
import com.birkredit.repository.customer.CustomerRepository;
import com.birkredit.repository.credit.CreditRepository;
import com.birkredit.service.credit.CreditService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.Optional;

@Service
public class CreditServiceImpl implements CreditService {

    private CustomerRepository customerRepository;
    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CustomerRepository customerRepository, CreditRepository creditRepository) {
        this.customerRepository = customerRepository;
        this.creditRepository = creditRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreditResponse issueCredit(String customerNumber, CreditRequest request) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerNumber(customerNumber);
        optionalCustomer.orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CUSTOMER_NOT_FOUND_BY_CUSTOMER_NUMBER));
        Customer customer = optionalCustomer.get();

        Credit credit = calculateAndSaveCredit(customer, request);
        Credit updatedCredit = creditRepository.save(credit);

        CustomerLiteResponse customerLiteResponse = CustomerMapper.INSTANCE.customerToCustomerLiteResponse(customer);
        CreditResponse creditResponse = CreditMapper.INSTANCE.creditToCreditResponse(updatedCredit);
        creditResponse.setCustomerLiteResponse(customerLiteResponse);

        return creditResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Credit calculateAndSaveCredit(Customer customer, CreditRequest request) {
        Credit credit = CreditMapper.INSTANCE.creditRequestToCredit(request);
        credit.setCreditNumber(RandomStringUtils.random(20, false, true));
        credit.setDebt(request.getAmount());

        credit.setMonths((double) Period.between(credit.getStartDate(), credit.getEndDate()).toTotalMonths());
        credit.setPercentagePerMonth(credit.getPercentage() / (credit.getMonths() * 100D));
        credit.setPaymentPerMonth((credit.getAmount() * credit.getPercentagePerMonth()) / (1D - (1D / (Math.pow(1D + credit.getPercentagePerMonth(), credit.getMonths())))));
        credit.setCustomer(customer);

        for (int i = 1; i <= credit.getMonths(); i++) {
            Payment payment = new Payment();
            payment.setPaymentNumber(RandomStringUtils.random(20, false, true));
            payment.setPaymentDate(credit.getStartDate().plusMonths(i));
            payment.setInterestAmountOfMonth(credit.getDebt() * credit.getPercentagePerMonth());
            payment.setMainAmountOfMonth((credit.getPaymentPerMonth() - payment.getInterestAmountOfMonth()));
            credit.setDebt(credit.getDebt() - payment.getMainAmountOfMonth());
            payment.setIsPayed(false);

            credit.addPayment(payment);
        }

        credit.setDebt(request.getAmount());

        return creditRepository.save(credit);
    }
}
