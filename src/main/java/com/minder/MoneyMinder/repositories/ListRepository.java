package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {

    boolean existsByListIdAndUserId(Long listId, Long userId);

    List<ListEntity> findAllByUserId(Long userId);

    @Query("SELECT SUM(l.price) FROM ItemEntity l WHERE l.listId = :listId AND l.userId = :userId")
    double findTotalAmountByListIdAndUserId(Long listId, Long userId);
}
