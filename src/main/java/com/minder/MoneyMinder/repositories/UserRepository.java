package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.controllers.user.dto.UserModel;
import com.minder.MoneyMinder.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
