package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemRepository extends JpaRepository<UserItemEntity, Long> {
}
