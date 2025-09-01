package com.sharik.user_service.dto;

import com.sharik.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Role role;
}
