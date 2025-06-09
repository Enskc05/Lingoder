package com.lingoder.v1.controller;

import com.lingoder.v1.dto.*;
import com.lingoder.v1.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto request){
        authService.signUp(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto request){
        return ResponseEntity.ok(authService.signIn(request));
    }
    @PostMapping("/forgot")
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPassword request){
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequestDto request){
        authService.resetPassword(request);
        return ResponseEntity.ok().build();
    }
}


