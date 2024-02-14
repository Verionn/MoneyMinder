package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.PurchasedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItemEntity, Long> {
    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.categoryId = :categoryId AND l.userId = :userId")
    List<PurchasedItemEntity> findAllByCategoryIdAndUserId(Long categoryId, Long userId);

    @Query("SELECT DISTINCT l FROM PurchasedItemEntity l WHERE LOWER(l.name) LIKE LOWER(:prefix || '%') AND l.userId = :userId")
    List<PurchasedItemEntity> findAllByPrefixAndUserId(String prefix, Long userId);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.categoryId = :categoryId AND l.userId = :userId AND FUNCTION('DATEDIFF', DAY, l.timeBought, CURRENT_DATE()) <= :days")
    List<PurchasedItemEntity> findAllByCategoryIdAndUserIdInLastNDays(Long categoryId, Long days, Long userId);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE FUNCTION('DATEDIFF', DAY, l.timeBought, CURRENT_DATE()) <= :days AND l.userId = :userId")
    List<PurchasedItemEntity> findAllInLastNDaysAndByUserId(Long days, Long userId);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.userId = :userId ORDER BY timeBought DESC LIMIT :amountOfItems")
    List<PurchasedItemEntity> findAllByDateBoughtAndUserId(Long amountOfItems, Long userId);

    @Query("SELECT l FROM PurchasedItemEntity l WHERE l.listId = :listId AND l.userId = :userId")
    List<PurchasedItemEntity> findAllByListIdAndUserId(Long listId, Long userId);
}
