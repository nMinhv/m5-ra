package com.ra.controller;

import com.ra.model.dto.request.SignInRequest;
import com.ra.model.dto.request.SignUpRequest;
import com.ra.service.UserService;
import com.ra.util.exception.BadRequestException;
import com.ra.util.exception.NotFoundException;
import com.ra.util.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUp) throws NotFoundException, BadRequestException {

        return new ResponseEntity<>(userService.signUp(signUp), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signIn) throws NotFoundException, UnAuthorizedException {
        return new ResponseEntity<>(userService.signIn(signIn),HttpStatus.OK);
    }

}

