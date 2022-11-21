package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, Long> {



}
