package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import com.softlaboratory.hafyapi.service.RolesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService service;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() {
        try {
            return service.getAll();
        }catch (Exception e) {
            log.error("Error get all : {}", e.getMessage());
            log.trace("Trace error : ",e);
            throw e;
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return service.getById(id);
        }catch (Exception e) {
            log.error("Error get by id : {}", e.getMessage());
            log.trace("Trace error : ",e);
            throw e;
        }
    }

    @GetMapping(value = "/q")
    public ResponseEntity<Object> getByRole(@RequestParam(value = "role") String role) {
        try {
            return service.getByRole(role);
        }catch (Exception e) {
            log.error("Error get by role : {}", e.getMessage());
            log.trace("Trace error : ",e);
            throw e;
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createRole(@RequestBody RolesDto req) {
        try {
            return service.create(req);
        }catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody RolesDto req) {
        try {
            return service.updateById(id, req);
        }catch (Exception e) {
            log.error("Error updating role : {}", e.getMessage());
            log.trace("Trace error : ",e);
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id) {
        try {
            return service.deleteById(id);
        }catch (Exception e) {
            log.error("Error deleting role : {}", e.getMessage());
            log.trace("Trace error : ",e);
            throw e;
        }
    }

}
