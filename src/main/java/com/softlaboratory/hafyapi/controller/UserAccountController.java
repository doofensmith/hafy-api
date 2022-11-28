package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/uac/manage")
public class UserAccountController {

    @Autowired
    private AccountService service;

    @PostMapping(value = "/add-role/{id}")
    public ResponseEntity<Object> addRole(@PathVariable Long id, @RequestBody RoleEnum role) {
        try {
            return service.addRole(id, role);
        }catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(value = "/user-list")
    public ResponseEntity<Object> listUser() {
        try {
            return service.listUser();
        }catch (Exception e) {
            throw e;
        }
    }

}
