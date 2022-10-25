package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.TypeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeDao, Long>, JpaSpecificationExecutor<TypeDao> {
}