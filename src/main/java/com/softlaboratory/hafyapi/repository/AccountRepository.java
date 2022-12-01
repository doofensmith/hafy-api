package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, Long> {

    @Query("select a from AccountDao a where upper(a.username) = upper(?1)")
    Optional<AccountDao> findByUsername(String username);

    AccountDao getDistinctTopByUsername(String username);

    @Transactional
    @Modifying
    @Query("update AccountDao a set a.password = ?2 where a.id = ?1")
    void updatePasswordByIdEquals(Long id, @NonNull String password);

    @Transactional
    @Modifying
    @Query("update AccountDao a set a.isActive = ?2 where a.id = ?1")
    void updateIsActiveByIdEquals(Long id, @NonNull boolean isActive);

    @Transactional
    @Modifying
    @Query("update AccountDao a set a.wrongPasswordCounter = ?2 where a.id = ?1")
    void updateWrongPasswordCounterByIdEquals(Long id, @NonNull Byte wrongPasswordCounter);

    @Transactional
    @Modifying
    @Query("update AccountDao a set a.oneTimePassword = ?2 where a.id = ?1")
    void updateOneTimePasswordByIdEquals(Long id, String oneTimePassword);

}
