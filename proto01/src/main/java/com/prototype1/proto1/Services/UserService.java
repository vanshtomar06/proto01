package com.prototype1.proto1.Services;

import com.prototype1.proto1.Models.User;
import com.prototype1.proto1.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return user;
    }

    public List<User> allUser(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
