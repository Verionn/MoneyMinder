package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByListIdAndUserId(Long listId, Long userId);

    void deleteAllByListId(Long listId);

    boolean existsByItemIdAndUserId(Long itemId, Long userId);
}
