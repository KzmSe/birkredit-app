package com.birkredit.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditResponse {

    private CustomerLiteResponse customerLiteResponse;
    private Long id;
    private String creditNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double amount;
    private Double debt;
    private Double percentage;
    private Double months;
    private Double percentagePerMonth;
    private Double paymentPerMonth;
    private List<PaymentResponse> payments = new ArrayList<>();

    public void addPaymentResponse(PaymentResponse paymentResponse) {
        this.payments.add(paymentResponse);
    }
}
