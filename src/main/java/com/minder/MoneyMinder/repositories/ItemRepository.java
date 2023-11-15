package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByListId(Long listId);

    void deleteAllByListId(Long listId);
}
