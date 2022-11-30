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
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.Random;

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

            log.debug("Find account with repository.");
            Optional<AccountDao> account = accountRepository.findByUsername(request.getUsername());

            log.debug("Check account active.");
            if (account.isPresent() && !account.get().isActive()) {
                log.debug("Account not activated.");

                log.info("Login failed.");
                throw new UsernameNotFoundException("Account not found.");
            }

            log.debug("Check account password.");
            if (account.isPresent() && !passwordEncoder.matches(request.getPassword(), account.get().getPassword())) {
                log.debug("Wrong password.");

                log.debug("Updating wrong password counter with repository.");
                wrongPassword(account.get().getId(), accountRepository, account.get().getWrongPasswordCounter());

                log.info("Login failed.");
                throw new BadCredentialsException("Wrong password!");
            }

            if (account.get().getWrongPasswordCounter() != 0) {
                log.debug("Reset wrong password counter.");
                accountRepository.updateWrongPasswordCounterByIdEquals(account.get().getId(), (byte)0);
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
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<Object> register(RegisterRequest request) {
        log.info("Starting regiter account.");

        log.debug("Register request : {}", request);

        log.debug("Check duplicate username with repository.");
        Optional<AccountDao> accountDuplicate = accountRepository.findByUsername(request.getUsername());
        if (accountDuplicate.isPresent()) {
            log.debug("Register failed due to duplicate username.");
            throw new DataIntegrityViolationException("Duplicate Username.");
        }

        log.debug("Preparing new account.");
        //BASE ACCOUNT ROLE AND TYPE
        RoleDao role = roleRepository.findByRoleEquals(RoleEnum.USER);
        TypeDao type = typeRepository.findByTypeEquals(AccountTypeEnum.CUSTOMER);

        log.debug("Save profile with repository.");
        ProfileDao profileDao = new ProfileDao();
        profileDao.setFullname(request.getFullname());

        profileDao = profileRepository.save(profileDao);

        log.debug("Preparing new account.");
        String otp = generateOtp();

        log.debug("Save new account with repository.");
        AccountDao account = new AccountDao();
        account.setProfile(profileDao);
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setOneTimePassword(otp);
        account.setRoles(List.of(role));
        account.setTypes(List.of(type));

        account = accountRepository.save(account);

        return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public ResponseEntity<Object> verification(Long id, String requestOtp) {
        log.info("Starting account verification.");
        log.debug("Req body : {}", requestOtp);

        log.debug("Find account to verify with repository.");
        Optional<AccountDao> account = accountRepository.findById(id);
        if (account.isPresent()) {
            if (account.get().getOneTimePassword().equals(requestOtp)) {
                log.debug("Update account active status with repository.");
                accountRepository.updateIsActiveByIdEquals(id, true);

                log.debug("Remove OTP with repository.");
                accountRepository.updateOneTimePasswordByIdEquals(id, null);

                log.info("Account verification success.");
                return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
            }else {
                log.debug("OTP did not match.");

                log.info("Verify account failed.");
                throw new BadCredentialsException("Wrong OTP!");
            }
        }else {
            log.debug("Account {} not found.", id);

            log.info("Verify account failed.");
            throw new UsernameNotFoundException("User not found.");
        }
    }

    @Override
    public ResponseEntity<Object> reset(Long id) {
        log.info("Starting reset account.");

        log.debug("Find account to reset with repository.");
        Optional<AccountDao> account = accountRepository.findById(id);
        if (account.isPresent()) {
            log.debug("Generate OTP.");
            String otp = generateOtp();

            log.debug("Update account otp with repository.");
            accountRepository.updateOneTimePasswordByIdEquals(id, otp);

            log.info("Set OTP to account success.");
            return ResponseUtil.build(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null);
        }else {
            throw new UsernameNotFoundException("Account nor found.");
        }
    }


    private String generateOtp() {
        Random random = new Random();
        Integer otp = random.nextInt(999999);

        return String.format("%06d", otp);
    }

    private void wrongPassword(Long id, AccountRepository repository, Byte counter) {
        if (counter > 2) {
            log.debug("Wrong password more than 3 times.");
            log.debug("Account deactivated.");
            repository.updateIsActiveByIdEquals(id, false);
        }else {
            log.debug("Wrong password counter {}.", (counter+1));
            repository.updateWrongPasswordCounterByIdEquals(id, (byte) (counter+1));
        }
    }

}
