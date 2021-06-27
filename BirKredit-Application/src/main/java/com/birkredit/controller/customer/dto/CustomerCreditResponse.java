package com.birkredit.controller.customer.dto;

import com.birkredit.controller.credit.dto.CreditResponse;
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
public class CustomerCreditResponse {

    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private List<CreditResponse> credits = new ArrayList<>();
}
