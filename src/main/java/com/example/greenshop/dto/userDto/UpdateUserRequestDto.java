package com.example.greenshop.dto.userDto;

import com.example.greenshop.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String house;
    private String street;
    private String city;
    private String postalCode;
}
