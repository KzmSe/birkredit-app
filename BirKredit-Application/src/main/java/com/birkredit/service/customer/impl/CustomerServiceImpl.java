package com.birkredit.service.customer.impl;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.customer.dto.CustomerResponse;
import com.birkredit.entity.Customer;
import com.birkredit.exception.DataNotFoundException;
import com.birkredit.exception.response.ResponseMessage;
import com.birkredit.mapper.CustomerMapper;
import com.birkredit.repository.customer.CustomerJpaRepository;
import com.birkredit.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    public CustomerServiceImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public CustomerResponse insertCustomer(Customer customer) {
        Customer savedCustomer = customerJpaRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerCreditResponse findByCustomerNumber(String customerNumber) {
        Optional<Customer> optionalCustomer = customerJpaRepository.findByCustomerNumber(customerNumber);
        optionalCustomer.orElseThrow(() -> new DataNotFoundException(ResponseMessage.ERROR_CUSTOMER_NOT_FOUND_BY_CUSTOMER_NUMBER));

        return CustomerMapper.INSTANCE.customerToCustomerCreditResponse(optionalCustomer.get());
    }
}
