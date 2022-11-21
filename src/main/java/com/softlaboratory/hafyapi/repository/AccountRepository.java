package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, Long> {

    @Query("select a from AccountDao a where upper(a.username) = upper(?1)")
    Optional<AccountDao> findByUsername(String username);

}
