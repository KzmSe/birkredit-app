package com.birkredit.controller.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {

    private Long id;
    private String paymentNumber;
    private LocalDate paymentDate;
    private Double amountOfPayment;
    private Double durationPerMonth;
    private Double mainAmountOfMonth;
    private Double interestAmountOfMonth;
    private Boolean isPayed;
}
