package com.softlaboratory.hafyapi.controller;

import com.softlaboratory.hafyapi.constant.AppConstant;
import com.softlaboratory.hafyapi.domain.dto.TypeDto;
import com.softlaboratory.hafyapi.kafka.KafkaProducer;
import com.softlaboratory.hafyapi.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping(value = "/types")
public class TypeController {

    @Autowired
    private TypeService service;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaTemplate<String, ResponseEntity<Object>> template;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() throws ExecutionException, InterruptedException {
        try {
            return producer.sendMessage().get().getProducerRecord().value();
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
