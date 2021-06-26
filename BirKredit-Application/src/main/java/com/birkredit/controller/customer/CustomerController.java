package com.birkredit.controller.customer;

import com.birkredit.controller.dto.CustomerCreationRequest;
import com.birkredit.controller.dto.CustomerCreditResponse;
import com.birkredit.controller.dto.CustomerResponse;
import com.birkredit.entity.Customer;
import com.birkredit.mapper.CustomerMapper;
import com.birkredit.service.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customers")
@Api(value = "Operations related to customers in BirKredit Application")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    @ApiOperation(value = "Create a new customer", response = CustomerResponse.class)
    public CustomerResponse register(@RequestBody CustomerCreationRequest request) {
        Customer customer = CustomerMapper.INSTANCE.customerCreationRequestToCustomer(request);
        customer.setCustomerNumber(RandomStringUtils.random(20, false, true));

        return customerService.insertCustomer(customer);
    }

    @GetMapping("/{customer-number}")
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    @ApiOperation(value = "Find customer by customer number", response = CustomerCreditResponse.class)
    public CustomerCreditResponse findByCustomerNumber(@PathVariable(name = "customer-number") String customerNumber) {
        return customerService.findByCustomerNumber(customerNumber);
    }
}
