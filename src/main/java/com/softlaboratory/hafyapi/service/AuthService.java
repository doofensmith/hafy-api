package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.domain.request.ChangePasswordRequest;
import com.softlaboratory.hafyapi.domain.request.LoginRequest;
import com.softlaboratory.hafyapi.domain.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    ResponseEntity<Object> login(LoginRequest request);
    ResponseEntity<Object> register(RegisterRequest request);
    ResponseEntity<Object> verification(Long id, String requestOtp);
    ResponseEntity<Object> reset(Long id);
    ResponseEntity<Object> changePassword(Long id, ChangePasswordRequest request);

}
