package com.birkredit.controller.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerLiteResponse {

    private Long id;
    private String customerNumber;
    private String name;
    private String surname;
}
