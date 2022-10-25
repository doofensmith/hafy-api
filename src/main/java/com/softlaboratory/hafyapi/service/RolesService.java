package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RolesService {

    ResponseEntity<Object> getAll();
    ResponseEntity<Object> getById(Long id);
    ResponseEntity<Object> getByRole(String role);
    ResponseEntity<Object> create(RolesDto req);
    ResponseEntity<Object> updateById(Long id, RolesDto req);
    ResponseEntity<Object> deleteById(Long id);

}
