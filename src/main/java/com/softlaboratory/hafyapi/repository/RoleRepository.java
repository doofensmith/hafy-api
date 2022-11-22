package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.constant.enums.RoleEnum;
import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RoleRepository extends JpaRepository<RoleDao, Long> {

    @Query("select r from RoleDao r where r.role = ?1")
    RoleDao findByRoleEquals(RoleEnum role);

}
