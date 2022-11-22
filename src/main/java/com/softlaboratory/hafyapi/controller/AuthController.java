package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.domain.request.LoginRequest;
import com.softlaboratory.hafyapi.domain.request.RegisterRequest;
import com.softlaboratory.hafyapi.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        try {
            return service.login(request);
        }catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        try {
            return service.register(request);
        }catch (Exception e) {
            throw e;
        }
    }

}
