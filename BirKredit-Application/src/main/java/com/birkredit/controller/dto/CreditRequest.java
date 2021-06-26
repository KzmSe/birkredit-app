package com.birkredit.controller.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreditRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @Future
    private LocalDate endDate;
    @NotNull
    @Positive
    private Double durationPerMonth;
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    @Positive
    private Double percentage;
}
