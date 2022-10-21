package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.domain.assembler.RoleAssembler;
import com.softlaboratory.hafyapi.domain.dao.RolesDao;
import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import com.softlaboratory.hafyapi.repository.RolesRepository;
import com.softlaboratory.hafyapi.service.RolesService;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository repository;

    @Autowired
    private RoleAssembler assembler;

    @Override
    public ResponseEntity<Object> getAll() {
        log.info("Starting get all role data.");

        log.debug("Fetching data with repository.");
        List<RolesDao> rolesDaoList = repository.findAll();

        log.debug("Converting to data presentation.");
        List<RolesDto> rolesDtoList = new ArrayList<>();
        assembler.convertToDtoList(rolesDaoList);

        log.info("Get all role data success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), rolesDtoList);
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        log.info("Starting get role by id.");
        log.debug("Id : {}", id);
        log.debug("Fetching data with repository.");
        Optional<RolesDao> dao = repository.findById(id);
        if (dao.isEmpty()) {
            log.info("Failed get role by id.");
            log.debug("Role with id : {} not found.", id);
            return ResponseUtil.build(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null);
        }else {
            log.info("Get role by id success.");
            return ResponseUtil.build(
                    HttpStatus.OK,
                    HttpStatus.OK.getReasonPhrase(),
                    assembler.convertToDto(dao.get())
            );
        }

    }

    @Override
    public ResponseEntity<Object> getByRole(String role) {
        log.info("Starting get role by role name.");
        log.debug("Role : {}", role);
        log.debug("Fetching role data with repository.");
        Optional<RolesDto> dto = repository.findByRoleEqualsIgnoreCase(role);
        if (dto.isPresent()) {
            log.info("Success get role by role name.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dto);
        }else {
            log.info("Failed get role by role name.");
            log.debug("Role : {} not found.", role);
            return ResponseUtil.build(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> create(RolesDto req) {
        log.info("Starting create new role.");
        log.debug("Request : {}", req);
        log.debug("Saving new role data.");
        RolesDao dao = assembler.convertToDao(req);
        dao = repository.save(dao);
        log.info("Save new role data success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public ResponseEntity<Object> updateById(Long id, RolesDto req) {
        log.info("Starting update role data.");
        log.debug("Data id : {}, request : {}", id, req);
        log.debug("Find old role data.");
        Optional<RolesDao> rolesDaoOld = repository.findById(id);
        if (rolesDaoOld.isPresent()) {
            log.debug("Updating role data.");
            RolesDao rolesDaoNew = rolesDaoOld.get();
            rolesDaoNew = assembler.convertToDao(req);
            rolesDaoNew = repository.save(rolesDaoNew);
            log.info("Update role data success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.info("Failed update role data.");
            log.debug("Role with id : {} not found.", id);
            return ResponseUtil.build(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        log.info("Starting delete role data.");
        log.debug("Delete role id : {}", id);
        log.debug("Find role data to delete.");
        Optional<RolesDao> rolesDao = repository.findById(id);
        if (rolesDao.isPresent()) {
            log.debug("Deleting role data.");
            repository.deleteById(id);
            log.info("Deleting role data success.");
            return null;
        }else {
            log.info("Delete role data failed.");
            log.debug("Role data with id : {} not found.", id);
            return ResponseUtil.build(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null);
        }
    }

}
