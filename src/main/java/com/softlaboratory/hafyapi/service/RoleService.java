package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.domain.dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    ResponseEntity<Object> getAll();
    ResponseEntity<Object> getById(Long id);
    ResponseEntity<Object> create(RoleDto req);
    ResponseEntity<Object> updateById(Long id, RoleDto req);
    ResponseEntity<Object> deleteById(Long id);

}
