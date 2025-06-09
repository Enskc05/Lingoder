package com.lingoder.v1.controller;

import com.lingoder.v1.dto.UserInfoResponseDto;
import com.lingoder.v1.dto.UserUpdateRequestDto;
import com.lingoder.v1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UserUpdateRequestDto requestDto){
       userService.update(id,requestDto);
        return ResponseEntity.ok().build();
    }
}
