package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.domain.request.LoginRequest;
import com.softlaboratory.hafyapi.domain.request.RegisterRequest;
import com.softlaboratory.hafyapi.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/verification/{id}")
    public ResponseEntity<Object> verification(@PathVariable Long id, @RequestBody String requestOtp) {
        try {
            return service.verification(id, requestOtp);
        }catch (Exception e) {
            throw e;
        }
    }

    @PostMapping(value = "/reset-account/{id}")
    public ResponseEntity<Object> resetAccount(@PathVariable Long id) {
        try {
            return service.reset(id);
        }catch (Exception e) {
            throw e;
        }
    }

}
