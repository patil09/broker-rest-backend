package com.radianbroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.RisUser;

@Repository
public interface RisUserRepository extends JpaRepository<RisUser, Long> {

    @Query(value = "SELECT * FROM ris_users WHERE user_id =?1 AND ris_id=?2", nativeQuery = true)
    RisUser findByUserIdAndRisId(Long userId, Long risId);
}
