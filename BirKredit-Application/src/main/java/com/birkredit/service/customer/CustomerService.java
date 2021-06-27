package com.birkredit.service.customer;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.customer.dto.CustomerResponse;
import com.birkredit.entity.Customer;

public interface CustomerService {

    CustomerResponse insertCustomer(Customer customer);

    CustomerCreditResponse findByCustomerNumber(String customerNumber);
}
