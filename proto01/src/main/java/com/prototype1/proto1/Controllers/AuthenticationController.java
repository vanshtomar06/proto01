package com.prototype1.proto1.Controllers;

import com.prototype1.proto1.Models.LoginDto;
import com.prototype1.proto1.Models.RegisterDto;
import com.prototype1.proto1.Models.User;
import com.prototype1.proto1.Models.VerifyUserDto;
import com.prototype1.proto1.Responses.LoginResponse;
import com.prototype1.proto1.Services.AuthenticationSerivce;
import com.prototype1.proto1.Services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationSerivce authenticationSerivce;

    public AuthenticationController(JwtService jwtService, AuthenticationSerivce authenticationSerivce) {
        this.jwtService = jwtService;
        this.authenticationSerivce = authenticationSerivce;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        User registeredUser = authenticationSerivce.signup(registerDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginDto){
        User authenticatedUser = authenticationSerivce.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto){
        try {
            authenticationSerivce.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account Verified");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email){
        try{
            authenticationSerivce.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
