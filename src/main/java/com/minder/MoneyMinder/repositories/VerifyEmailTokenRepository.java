package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.VerifyEmailTokenEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerifyEmailTokenRepository extends JpaRepository<VerifyEmailTokenEntity, Long> {
    @Query("SELECT l.email FROM VerifyEmailTokenEntity l WHERE l.token = :token")
    String findEmailByToken(String token);

    Boolean existsByToken(String token);

    VerifyEmailTokenEntity getTokenEntityByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
