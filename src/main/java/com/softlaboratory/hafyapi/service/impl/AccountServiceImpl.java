package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.repository.AccountRepository;
import com.softlaboratory.hafyapi.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
