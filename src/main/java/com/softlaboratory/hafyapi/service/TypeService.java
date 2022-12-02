package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.domain.dto.TypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TypeService {

    ResponseEntity<Object> getAll();
    ResponseEntity<Object> getById(Long id);
    ResponseEntity<Object> create(TypeDto req);
    ResponseEntity<Object> update(Long id, TypeDto req);
    ResponseEntity<Object> delete(Long id);

}
