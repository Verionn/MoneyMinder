package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.PurchasedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItemEntity, Long> {
    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.categoryId = :categoryId")
    List<PurchasedItemEntity> findAllByCategoryId(Long categoryId);
}
