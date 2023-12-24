package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.models.PurchasedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItemEntity, Long> {
    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.categoryId = :categoryId")
    List<PurchasedItemEntity> findAllByCategoryId(Long categoryId);

    @Query("SELECT DISTINCT l FROM PurchasedItemEntity l WHERE l.name LIKE :prefix || '%'")
    List<PurchasedItemEntity> findAllByPrefix(String prefix);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.categoryId = :categoryId AND FUNCTION('DATEDIFF', DAY, l.timeBought, CURRENT_DATE()) <= :days")
    List<PurchasedItemEntity> findAllByCategoryIdInLastNDays(Long categoryId, Long days);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE FUNCTION('DATEDIFF', DAY, l.timeBought, CURRENT_DATE()) <= :days")
    List<PurchasedItemEntity> findAllInLastNDays(Long days);

    @Query("SELECT l FROM PurchasedItemEntity l ORDER BY timeBought DESC LIMIT :amountOfItems")
    List<PurchasedItemEntity> findAllByDateBought(Long amountOfItems);
}
