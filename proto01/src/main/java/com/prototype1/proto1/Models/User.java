package com.prototype1.proto1.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(
            name = "fname",
            length = 15,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "lname",
            length = 15,
            nullable = false
    )
    private String lastName;

    @Column(unique = true, nullable = false)
    private String emailId;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;

    private Boolean enabled;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verificationCode_expires")
    private LocalDateTime verificationCodeExpires;

    public User(String firstName, String lastName, String emailId, String phoneNumber, Integer age, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.password = password;
        this.username = username;
    }

    public User(){ }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
