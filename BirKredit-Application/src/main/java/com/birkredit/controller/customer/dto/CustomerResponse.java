package com.birkredit.controller.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String customerNumber;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
}
