package com.prototype1.proto1.Repositories;

import com.prototype1.proto1.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmailId(String emailId);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Optional<User> findUserByVerificationCode(String ver);
    Boolean existsByEmailId(String emailId);
    Boolean existsByPhoneNumber(String p);
}
