package com.birkredit.repository.customer;

import com.birkredit.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByCustomerNumberAndCreditNumber(String number, String creditNumber);
}
