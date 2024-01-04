package com.ra.controller;

import com.ra.dto.rq.UserRequestLogin;
import com.ra.dto.rq.UserRequestRegister;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
@GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestLogin login){

    return new ResponseEntity<>(userService.login(login),HttpStatus.OK);
}
@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestRegister user){

    return new ResponseEntity<>(userService.register(user),HttpStatus.OK);
}
}

