package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import com.softlaboratory.hafyapi.domain.dao.TypeDao;
import com.softlaboratory.hafyapi.domain.response.ListUserResponse;
import com.softlaboratory.hafyapi.repository.AccountRepository;
import com.softlaboratory.hafyapi.repository.ProfileRepository;
import com.softlaboratory.hafyapi.repository.RoleRepository;
import com.softlaboratory.hafyapi.repository.TypeRepository;
import com.softlaboratory.hafyapi.service.AccountService;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by username using repository.");
        AccountDao account = accountRepository.getDistinctTopByUsername(username);

        if (account != null) {
            log.debug("Load user success.");
            return account;
        }else {
            log.debug("Load user failed.");
            throw new UsernameNotFoundException("Username not found.");
        }

    }

}
