package com.minder.MoneyMinder.repositories;

import com.minder.MoneyMinder.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
