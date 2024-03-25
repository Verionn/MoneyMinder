package com.minder.MoneyMinder.repositories;


import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import com.minder.MoneyMinder.controllers.user.dto.UserModel;
import com.minder.MoneyMinder.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u.verified FROM UserEntity u WHERE u.email = :email")
    boolean checkIfUserIsVerified(String email);
}
