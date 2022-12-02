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
import com.softlaboratory.hafyapi.service.AdministratorService;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TypeRepository typeRepository;

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

            log.info("Add user role success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("User id {} not found.", id);

            log.info("Add user role failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }

    }

    @Override
    public ResponseEntity<Object> addType(Long id, AccountTypeEnum accountType) {
        log.info("Starting add type to user.");

        log.debug("Find user to add type with repository.");
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (accountDao.isPresent()) {
            log.debug("Find type to add with repository.");
            TypeDao typeToAdd = typeRepository.findByTypeEquals(accountType);

            AccountDao addedType = accountDao.get();
            addedType.getTypes().add(typeToAdd);

            log.debug("Save added type with repository.");
            addedType = accountRepository.save(addedType);

            log.info("Add user type success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("User id {} not found.", id);

            log.info("Add user type failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> removeRole(Long id, RoleEnum role) {
        log.info("Starting remove role from user.");

        log.debug("Find user to remove role with repository.");
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (accountDao.isPresent()) {
            log.debug("Find role to remove with repository.");
            RoleDao roleToRemove = roleRepository.findByRoleEquals(role);

            AccountDao removedRole = accountDao.get();
            if (removedRole.getRoles().stream().anyMatch(roleDao -> roleDao.equals(roleToRemove))) {
                removedRole.getRoles().remove(roleToRemove);

                log.debug("Save removed role with repository.");
                removedRole = accountRepository.save(removedRole);

                log.info("Remove user role success.");
                return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
            }else {
                log.debug("Role {} not found.", role);

                log.info("Remove user role failed.");
                return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
            }
        }else {
            log.debug("User id {} not found.", id);

            log.info("Remove user role failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> removeType(Long id, AccountTypeEnum accountType) {
        log.info("Starting remove type from user.");

        log.debug("Find user to remove type with repository.");
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (accountDao.isPresent()) {
            log.debug("Find type to remove with repository.");
            TypeDao typeToRemove = typeRepository.findByTypeEquals(accountType);

            AccountDao removedType = accountDao.get();
            if (removedType.getTypes().stream().anyMatch(typeDao -> typeDao.equals(typeToRemove))) {
                removedType.getTypes().remove(typeToRemove);

                log.debug("Save removed type with repository.");
                removedType = accountRepository.save(removedType);

                log.info("Remove user type success.");
                return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
            }else {
                log.debug("Type {} not found.", accountType);

                log.info("Remove user type failed.");
                return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
            }
        }else {
            log.debug("User id {} not found.", id);

            log.info("Remove user type failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
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
        log.info("Starting set user active.");

        log.debug("Find user with repository.");
        Optional<AccountDao> account = accountRepository.findById(id);
        if (account.isPresent()) {
            log.debug("Preparing status active.");
            AccountDao accountSet = account.get();
            accountSet.setActive(userActive.booleanValue());

            log.debug("Save update user active with repository.");
            accountSet = accountRepository.save(accountSet);

            log.info("Set user active status success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("User id {} not found.", id);

            log.info("Set user active failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        log.info("Starting delete user by id.");

        log.debug("Find user to delete with repository.");
        Optional<AccountDao> account = accountRepository.findById(id);

        if (account.isPresent()) {
            log.debug("Delete user profile with repository.");
            profileRepository.delete(account.get().getProfile());

            log.debug("Delete user with repository.");
            accountRepository.deleteById(id);

            log.info("Delete user by id success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            log.debug("User id {} not found.", id);

            log.info("Delete user failed.");
            return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
        }
    }
}
