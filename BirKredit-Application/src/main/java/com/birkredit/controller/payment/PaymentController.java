package com.birkredit.controller.payment;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.payment.dto.PaymentRequest;
import com.birkredit.service.payment.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/payments")
@Api(value = "Operations related to payments in BirKredit Application")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/customers/{customer-number}/credits/{credit_number}")
    @ApiOperation(value = "Pay by customer numbers and credit number", response = CustomerCreditResponse.class)
    public CustomerCreditResponse payByCustomerNumberAndCreditNumber(@PathVariable(name = "customer-number") String customerNumber,
                                                                     @PathVariable(name = "credit_number") String creditNumber,
                                                                     @Valid @RequestBody PaymentRequest request) {
        return paymentService.payByCustomerNumberAndCreditNumber(customerNumber, creditNumber, request);
    }
}
