package com.project.ridebooking.rideApp.controllers;

import com.project.ridebooking.rideApp.dto.SignupDto;
import com.project.ridebooking.rideApp.dto.UserDto;
import com.project.ridebooking.rideApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signup(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }
}
