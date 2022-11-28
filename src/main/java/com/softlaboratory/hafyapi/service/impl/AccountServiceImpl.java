package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.repository.AccountRepository;
import com.softlaboratory.hafyapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDao account = repository.getDistinctTopByUsername(username);
        if (account != null) {
            return account;
        }else {
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
