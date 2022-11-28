package com.softlaboratory.hafyapi.service;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends UserDetailsService {

    ResponseEntity<Object> addRole(Long id, RoleEnum role);
    ResponseEntity<Object> addType(Long id, AccountTypeEnum accountType);
    ResponseEntity<Object> removeRole(Long id, RoleEnum role);
    ResponseEntity<Object> removeType(Long id, AccountTypeEnum accountType);
    ResponseEntity<Object> listUser();
    ResponseEntity<Object> setUserActive(Long id, Boolean userActive);
    ResponseEntity<Object> deleteUser(Long id);

}
