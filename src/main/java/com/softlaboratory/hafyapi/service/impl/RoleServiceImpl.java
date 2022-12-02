package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import com.softlaboratory.hafyapi.domain.dto.RoleDto;
import com.softlaboratory.hafyapi.repository.RoleRepository;
import com.softlaboratory.hafyapi.service.RoleService;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Object> getAll() {
        log.info("Starting get all role data.");

        log.debug("Fetching data with repository.");
        List<RoleDao> roleDaoList = repository.findAll();

        log.debug("Converting to data presentation.");
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleDao dao : roleDaoList) {
            roleDtoList.add(mapper.map(dao, RoleDto.class));
        }

        log.info("Get all role data success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), roleDtoList);
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        log.info("Starting get role by id.");

        log.debug("Fetching data id {} with repository.", id);
        Optional<RoleDao> dao = repository.findById(id);

        if (dao.isEmpty()) {
            log.debug("Role with id : {} not found.", id);

            log.info("Failed get role by id.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }else {
            log.debug("Converting to data presentation.");
            RoleDto dto =  mapper.map(dao.get(), RoleDto.class);

            log.info("Get role by id success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dto);
        }

    }

    @Override
    public ResponseEntity<Object> create(RoleDto req) {
        log.info("Starting create new role.");

        log.debug("Converting request : {} to data access.", req);
        RoleDao roleDao = mapper.map(req, RoleDao.class);

        log.debug("Saving new role data with repository.");
        roleDao = repository.save(roleDao);

        log.debug("Converting result to data transfer.");
        RoleDto dto = mapper.map(roleDao, RoleDto.class);

        log.info("Save new role data success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dto);
    }

    @Override
    public ResponseEntity<Object> updateById(Long id, RoleDto req) {
        log.info("Starting update role data.");

        log.debug("Find role id {} with repository.", id);
        Optional<RoleDao> rolesDaoOld = repository.findById(id);

        if (rolesDaoOld.isPresent()) {
            log.debug("Converting request : {} to data access.", req);
            RoleDao roleDaoNew = rolesDaoOld.get();
            roleDaoNew.setRole(req.getRole());

            log.debug("Saving new data with repository.");
            roleDaoNew = repository.save(roleDaoNew);

            log.debug("Converting result to data transfer.");
            RoleDto dto = mapper.map(roleDaoNew, RoleDto.class);

            log.info("Update role data success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dto);
        }else {
            log.debug("Role with id : {} not found.", id);

            log.info("Failed update role data.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        log.info("Starting delete role data.");

        log.debug("Find role data to delete id {} with repository.", id);
        Optional<RoleDao> rolesDao = repository.findById(id);

        if (rolesDao.isPresent()) {
            log.debug("Deleting role data with repository.");
            repository.deleteById(id);

            log.info("Deleting role data success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("Role data with id : {} not found.", id);

            log.info("Delete role data failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }

}
