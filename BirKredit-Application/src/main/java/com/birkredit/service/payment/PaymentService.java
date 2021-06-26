package com.birkredit.service.payment;

import com.birkredit.controller.dto.CustomerCreditResponse;

public interface PaymentService {

    CustomerCreditResponse payByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber, Double amount);
}
