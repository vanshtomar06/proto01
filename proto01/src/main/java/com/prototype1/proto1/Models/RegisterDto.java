package com.prototype1.proto1.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String password;
    private String username;
}
