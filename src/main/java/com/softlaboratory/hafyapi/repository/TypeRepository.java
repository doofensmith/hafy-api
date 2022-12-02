package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.constant.enums.AccountTypeEnum;
import com.softlaboratory.hafyapi.domain.dao.TypeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeDao, Long>, JpaSpecificationExecutor<TypeDao> {

    @Query("select t from TypeDao t where t.type = ?1")
    TypeDao findByTypeEquals(AccountTypeEnum type);

}