package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.domain.dto.TypeDto;
import com.softlaboratory.hafyapi.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping(value = "/types")
public class TypeController {

    @Autowired
    private TypeService service;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() throws ExecutionException, InterruptedException {
        try {
            return service.getAll();
        }catch (Exception e) {
            log.info("Error get all {}", e.getMessage());
            log.debug("Debug error", e);
            throw e;
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return service.getById(id);
        }catch (Exception e) {
            log.info("Error get by id {}", e.getMessage());
            log.debug("Debug error", e);
            throw e;
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> create(@RequestBody TypeDto req) {
        try {
            return service.create(req);
        }catch (Exception e) {
            log.info("Error create data {}", e.getMessage());
            log.debug("Debug error", e);
            throw e;
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody TypeDto req) {
        try {
            return service.update(id, req);
        }catch (Exception e) {
            log.info("Error update data {}", e.getMessage());
            log.debug("Debug error", e);
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            return service.delete(id);
        }catch (Exception e) {
            log.info("Error delete {}", e.getMessage());
            log.debug("Debug error", e);
            throw e;
        }
    }

}
