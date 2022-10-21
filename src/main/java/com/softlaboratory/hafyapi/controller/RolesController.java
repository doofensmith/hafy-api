package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService service;

    @GetMapping(value = "")
    public ResponseEntity<Object> findByRole(@RequestBody String role) {
        try {
            return service.getByRole(role);
        }catch (Exception e) {
            throw e;
        }
    }

}
