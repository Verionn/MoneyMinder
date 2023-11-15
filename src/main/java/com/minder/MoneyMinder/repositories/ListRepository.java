package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ListRepository extends JpaRepository<ListEntity, Long> {
    @Query("SELECT SUM(l.price) FROM ItemEntity l WHERE l.listId = :listId")
    double findTotalAmountByListId(@Param("listId") Long listId);
}
