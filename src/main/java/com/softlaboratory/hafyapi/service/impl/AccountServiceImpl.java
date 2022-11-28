package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.domain.dao.ProfileDao;
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

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TypeRepository typeRepository;

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

    @Override
    public ResponseEntity<Object> addRole(Long id, RoleEnum role) {
        log.info("Starting add role to user.");

        log.debug("Find user to add role with repository.");
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (accountDao.isPresent()) {
            log.debug("Find role to add with repository.");
            RoleDao roleToAdd = roleRepository.findByRoleEquals(role);

            AccountDao addedRole = accountDao.get();
            addedRole.getRoles().add(roleToAdd);

            log.debug("Save added role with repository.");
            addedRole = accountRepository.save(addedRole);

            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }

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
    public ResponseEntity<Object> listUser() {
        log.info("Starting listing user.");

        log.debug("Fetching user data with repository.");
        List<AccountDao> accountDaoList = accountRepository.findAll();

        log.debug("Convert result to data presentation.");
        List<ListUserResponse> listUsers = new ArrayList<>();
        for (AccountDao accountDao : accountDaoList) {
            listUsers.add(ListUserResponse.builder()
                            .idAccount(accountDao.getId())
                            .createdAt(accountDao.getCreatedAt())
                            .createdBy(accountDao.getCreatedBy())
                            .updatedAt(accountDao.getUpdatedAt())
                            .updatedBy(accountDao.getUpdatedBy())
                            .isDeleted(accountDao.getIsDeleted())
                            .deletedAt(accountDao.getDeletedAt())
                            .fullname(accountDao.getProfile().getFullname())
                            .username(accountDao.getUsername())
                            .password(accountDao.getPassword())
                            .isActive(accountDao.isActive())
                            .roles(accountDao.getRoles().stream().map(RoleDao::getRole).collect(Collectors.toList()))
                            .types(accountDao.getTypes().stream().map(TypeDao::getType).collect(Collectors.toList()))
                    .build());
        }

        log.info("Listing user success.");
        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), listUsers);
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
