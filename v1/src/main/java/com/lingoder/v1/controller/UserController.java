package com.lingoder.v1.controller;

import com.lingoder.v1.dto.UserInfoResponseDto;
import com.lingoder.v1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/info")
    public ResponseEntity<UserInfoResponseDto> info(){
        return ResponseEntity.ok(userService.getCurrentUserInfo());
    }
}
