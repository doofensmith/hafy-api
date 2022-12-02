package com.softlaboratory.hafyapi.repository;

import com.softlaboratory.hafyapi.domain.dao.ProfileDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileDao, Long> {
}
