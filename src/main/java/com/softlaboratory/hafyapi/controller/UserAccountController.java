package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
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

    @GetMapping(value = "/user-list")
    public ResponseEntity<Object> listUser() {
        try {
            return service.listUser();
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/add-role/{id}")
    public ResponseEntity<Object> addRole(@PathVariable Long id, @RequestBody RoleEnum role) {
        try {
            return service.addRole(id, role);
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/add-type/{id}")
    public ResponseEntity<Object> addType(@PathVariable Long id, @RequestBody AccountTypeEnum type) {
        try {
            return service.addType(id, type);
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/remove-role/{id}")
    public ResponseEntity<Object> removeRole(@PathVariable Long id, @RequestBody RoleEnum role) {
        try {
            return service.removeRole(id, role);
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/remove-type/{id}")
    public ResponseEntity<Object> removeType(@PathVariable Long id, @RequestBody AccountTypeEnum type) {
        try {
            return service.removeType(id, type);
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/set-active/{id}")
    public ResponseEntity<Object> setUserActive(@PathVariable Long id, @RequestBody Boolean userActive) {
        try {
            return service.setUserActive(id, userActive);
        }catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            return service.deleteUser(id);
        }catch (Exception e) {
            throw e;
        }
    }

}
