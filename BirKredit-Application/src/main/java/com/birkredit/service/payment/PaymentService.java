package com.birkredit.service.payment;

import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.payment.dto.PaymentRequest;

public interface PaymentService {

    CustomerCreditResponse payByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber, PaymentRequest request);
}
