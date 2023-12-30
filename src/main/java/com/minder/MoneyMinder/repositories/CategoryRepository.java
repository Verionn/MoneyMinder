package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserId(Long userId);

    //@Query("SELECT l FROM CategoryEntity l WHERE l.categoryId = :categoryId AND l.userId = :userId")
    boolean existsByCategoryIdAndUserId(Long categoryId, Long userId);
}
