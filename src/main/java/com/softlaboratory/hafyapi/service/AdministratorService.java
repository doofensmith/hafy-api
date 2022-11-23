package com.softlaboratory.hafyapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdministratorService {

    ResponseEntity<Object> addAccountRole();

}
