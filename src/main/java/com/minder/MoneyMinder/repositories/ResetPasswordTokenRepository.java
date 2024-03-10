package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.ResetPasswordTokenEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenEntity, Long> {
    @Query("SELECT l.email FROM ResetPasswordTokenEntity l WHERE l.token = :token")
    String findEmailByToken(String token);

    Boolean existsByToken(String token);

    ResetPasswordTokenEntity getTokenEntityByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
