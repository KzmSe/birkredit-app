package com.birkredit.controller.payment.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentRequest {

    @NotNull
    @Min(1)
    private Double amount;
    private String paymentNumber;
}
