package com.birkredit.service.payment;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.payment.dto.PaymentRequest;
import com.birkredit.entity.Credit;

public interface PaymentService {

    CustomerCreditResponse payByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber, PaymentRequest request);

    void updateCreditInfo(Credit credit, Double requestedAmount);
}
