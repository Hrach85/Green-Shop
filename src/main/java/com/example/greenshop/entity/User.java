package com.example.greenshop.entity;


import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
