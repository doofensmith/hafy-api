package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RolesService {

    public ResponseEntity<Object> getAll();
    public ResponseEntity<Object> getById(Long id);
    public ResponseEntity<Object> getByRole(String role);
    public ResponseEntity<Object> create(RolesDto req);
    public ResponseEntity<Object> updateById(Long id, RolesDto req);
    public ResponseEntity<Object> deleteById(Long id);

}
