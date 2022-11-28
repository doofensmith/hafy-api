package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.repository.AccountRepository;
import com.softlaboratory.hafyapi.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by username using repository.");
        AccountDao account = repository.getDistinctTopByUsername(username);

        if (account != null) {
            log.debug("Load user success.");
            return account;
        }else {
            log.debug("Load user failed.");
            throw new UsernameNotFoundException("Username not found.");
        }

    }

    @Override
    public ResponseEntity<Object> addRole(Long id, RoleEnum role) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addType(Long id, AccountTypeEnum accountType) {
        return null;
    }

    @Override
    public ResponseEntity<Object> removeRole(Long id, RoleEnum role) {
        return null;
    }

    @Override
    public ResponseEntity<Object> removeType(Long id, AccountTypeEnum accountType) {
        return null;
    }

    @Override
    public ResponseEntity<Object> setUserActive(Long id, Boolean userActive) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        return null;
    }

}
