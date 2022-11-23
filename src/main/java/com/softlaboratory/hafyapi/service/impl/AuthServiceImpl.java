package com.softlaboratory.hafyapi.service.impl;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import com.softlaboratory.hafyapi.domain.dao.ProfileDao;
import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import com.softlaboratory.hafyapi.domain.dao.TypeDao;
import com.softlaboratory.hafyapi.domain.request.LoginRequest;
import com.softlaboratory.hafyapi.domain.request.RegisterRequest;
import com.softlaboratory.hafyapi.domain.response.LoginResponse;
import com.softlaboratory.hafyapi.repository.AccountRepository;
import com.softlaboratory.hafyapi.repository.ProfileRepository;
import com.softlaboratory.hafyapi.repository.RoleRepository;
import com.softlaboratory.hafyapi.repository.TypeRepository;
import com.softlaboratory.hafyapi.security.JwtTokenProvider;
import com.softlaboratory.hafyapi.service.AuthService;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> login(LoginRequest request) {
        try {
            log.info("Starting account login.");

            log.debug("Login request : {}", request);
            Optional<AccountDao> account = accountRepository.findByUsername(request.getUsername());
            if (account.isPresent() && !passwordEncoder.matches(request.getPassword(), account.get().getPassword())) {
                throw new BadCredentialsException("Wrong password!");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            log.debug("Converting login response.");
            LoginResponse loginResponse = LoginResponse.builder()
                    .token(jwt)
                    .build();

            log.info("Login success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), loginResponse);
        }catch (BadCredentialsException e) {
            throw e;
        }catch (UsernameNotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<Object> register(RegisterRequest request) {
        log.info("Starting regiter account.");

        log.debug("Register request : {}", request);

        log.debug("Preparing new account.");
        //BASE ACCOUNT ROLE AND TYPE
        RoleDao role = roleRepository.findByRoleEquals(RoleEnum.USER);
        TypeDao type = typeRepository.findByTypeEquals(AccountTypeEnum.CUSTOMER);
//        List<RoleDao> roleList = new ArrayList<>();
//        roleList.add(role);
//        List<TypeDao> typeList = new ArrayList<>();
//        typeList.add(type);

        log.debug("Save profile with repository.");
        ProfileDao profileDao = ProfileDao.builder()
                .fullname(request.getFullname())
                .build();
        profileDao = profileRepository.save(profileDao);

        log.debug("Save new account with repository.");
        AccountDao account = AccountDao.builder()
                .profile(profileDao)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .roles(List.of(role))
                .types(List.of(type))
                .build();
        account = accountRepository.save(account);

        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
    }

}
