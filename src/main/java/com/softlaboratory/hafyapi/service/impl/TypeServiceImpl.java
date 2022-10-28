package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.domain.dao.TypeDao;
import com.softlaboratory.hafyapi.domain.dto.TypeDto;
import com.softlaboratory.hafyapi.repository.TypeRepository;
import com.softlaboratory.hafyapi.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Object> getAll() {
        log.info("Starting get all account type data.");
        log.debug("Fetching all data with repository.");
        List<TypeDao> typeDaoList = repository.findAll();
        List<TypeDto> typeDtoList = new ArrayList<>();
        log.debug("Convert to data transfer.");
        for (TypeDao typeDao : typeDaoList) {
            typeDtoList.add(mapper.map(typeDao, TypeDto.class));
        }
        log.info("Get all account type data success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), typeDtoList);
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        log.info("Starting get account type data by id.");
        log.debug("Find data id {} with repository.", id);
        Optional<TypeDao> typeDao = repository.findById(id);
        if (typeDao.isPresent()) {
            log.debug("Convert to data transfer object.");
            TypeDto dto = mapper.map(typeDao, TypeDto.class);
            log.info("Get account data by id success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dto);
        }else {
            log.debug("Account type data with id {} not found.", id);
            log.info("Get account type data failed.");
            return ResponseUtil.build(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    null);
        }
    }

    @Override
    public ResponseEntity<Object> create(TypeDto req) {
        log.info("Starting create new account type.");
        log.debug("Request body : {}", req);
        TypeDao typeDao = mapper.map(req, TypeDao.class);
        log.debug("Save new data with repository.");
        typeDao = repository.save(typeDao);
        log.debug("Convert new data result to data transfer.");
        TypeDto typeDto = mapper.map(typeDao, TypeDto.class);
        log.info("Creating new account type success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), typeDto);
    }

    @Override
    public ResponseEntity<Object> update(Long id, TypeDto req) {
        log.info("Starting update account type data.");
        log.debug("Find old data to update id {} with repository.", id);
        Optional<TypeDao> typeDaoOld = repository.findById(id);
        if (typeDaoOld.isPresent()) {
            log.debug("Request body : {}", req);
            TypeDao typeDaoNew = typeDaoOld.get();
            typeDaoNew.setType(req.getType().toString());
            log.debug("Update data with repository.");
            typeDaoNew = repository.save(typeDaoNew);
            log.debug("Convert updating result to data transfer.");
            TypeDto typeDto = mapper.map(typeDaoNew, TypeDto.class);
            log.info("Update account type data success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), typeDto);
        }else {
            log.debug("Account type data with id {} not found.", id);
            log.info("Update account type data failed.");
            return ResponseUtil.build(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        log.info("Starting delete account type data.");
        log.debug("Find data to delete id {} with repository.", id);
        Optional<TypeDao> typeDao = repository.findById(id);
        if (typeDao.isPresent()) {
            log.debug("Delete data with repository.");
            repository.deleteById(id);
            log.info("Delete account type data success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("Account type data with id {} not found.", id);
            log.info("Delete account type data failed.");
            return ResponseUtil.build(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    null);
        }
    }

}
