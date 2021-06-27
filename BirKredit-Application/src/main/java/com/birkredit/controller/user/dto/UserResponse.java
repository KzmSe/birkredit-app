package com.birkredit.controller.user.dto;

import com.birkredit.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String phoneNumber;
    private UserRole role;
}
