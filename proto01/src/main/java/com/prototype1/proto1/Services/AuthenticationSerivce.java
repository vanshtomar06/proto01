package com.prototype1.proto1.Services;

import com.prototype1.proto1.Models.LoginDto;
import com.prototype1.proto1.Models.RegisterDto;
import com.prototype1.proto1.Models.User;
import com.prototype1.proto1.Models.VerifyUserDto;
import com.prototype1.proto1.Repositories.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AuthenticationSerivce {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public User signup(RegisterDto input){
        User user = new User(input.getFirstName(),input.getLastName(), input.getEmail(),input.getPhoneNumber(),input.getAge(), passwordEncoder.encode(input.getPassword()), input.getUsername());
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpires(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);
        sendVerificationEmail(user);
        return userRepository.save(user);
    }

    public User authenticate(LoginDto input){
        User user = userRepository.findUserByEmailId(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.isEnabled()){
            throw new RuntimeException("Account not verified, please verify your account");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return user;
    }

    public void verifyUser(VerifyUserDto input){
        Optional<User> optionalUser = userRepository.findUserByEmailId(input.getEmail());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getVerificationCodeExpires().isBefore(LocalDateTime.now())){
                throw new RuntimeException("Verification code has expired");
            }
            if(user.getVerificationCode().equals(input.getVerificationCode())){
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpires(null);
                userRepository.save(user);
            }else{
                throw new RuntimeException("Invalid verification code");
            }
        }else{
            throw new RuntimeException("User not found");
        }
    }

    public void resendVerificationCode(String email){
        Optional<User> optionalUser = userRepository.findUserByEmailId(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.isEnabled()){
                throw new RuntimeException("User already verified");
            }else{
                user.setVerificationCode(generateVerificationCode());
                user.setVerificationCodeExpires((LocalDateTime.now().plusHours(1)));
                sendVerificationEmail(user);
                userRepository.save(user);
            }
        }else{
            throw new RuntimeException("User not found");
        }
    }

    public void sendVerificationEmail(User user){
        String subject = "Account Verification";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try{
            emailService.sendVerificationEmail(user.getEmailId(), subject, htmlMessage);
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    private String generateVerificationCode(){
        Random random = new Random();
        int code = random.nextInt(9000000) + 100000;
        return String.valueOf(code);
    }
}
