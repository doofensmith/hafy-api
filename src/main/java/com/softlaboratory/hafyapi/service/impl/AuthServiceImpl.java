package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.domain.request.LoginRequest;
import com.softlaboratory.hafyapi.domain.request.RegisterRequest;
import com.softlaboratory.hafyapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseEntity<Object> login(LoginRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> register(RegisterRequest request) {
        return null;
    }

}
