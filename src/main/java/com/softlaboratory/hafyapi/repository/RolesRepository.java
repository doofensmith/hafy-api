package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleDao, Long> {

}
