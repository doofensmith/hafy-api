package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.RolesDao;
import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesDao, Long> {

    @Query("select r from RolesDao r where upper(r.role) = upper(?1)")
    Optional<RolesDto> findByRoleEqualsIgnoreCase(String role);

}
