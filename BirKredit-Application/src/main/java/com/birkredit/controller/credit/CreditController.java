package com.birkredit.controller.credit;

import com.birkredit.controller.dto.CreditRequest;
import com.birkredit.controller.dto.CreditResponse;
import com.birkredit.controller.dto.CustomerCreditResponse;
import com.birkredit.service.credit.CreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/credits")
@Api(value = "Operations related to credits in BirKredit Application")
public class CreditController {

    private CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/customers/{customer-number}")
    @ApiOperation(value = "Issue a credit by customer number", response = CreditResponse.class)
    public CreditResponse issueCredit(@PathVariable(name = "customer-number") String customerNumber,
                                      @RequestBody CreditRequest request) {
        return creditService.issueCredit(customerNumber, request);
    }
}
