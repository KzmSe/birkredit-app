package com.birkredit.service.credit;

import com.birkredit.controller.dto.CreditRequest;
import com.birkredit.controller.dto.CreditResponse;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Customer;

public interface CreditService {

    CreditResponse issueCredit(String customerNumber, CreditRequest credit);

    Credit calculateAndSaveCredit(Customer customer, CreditRequest request);
}
