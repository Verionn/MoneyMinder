package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import com.minder.MoneyMinder.models.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<CategoryEntity> getCategory(Long categoryId);

    List<CategoryEntity> getCategories(Long userId);

    CategoryEntity addCategory(CategoryEntity categoryEntity);

    void deleteCategory(Long categoryId);

    Optional<CategoryEntity> updateCategory(Long categoryId, UpdateCategoryRequestBody updateCategoryRequestBody);

    boolean existsByCategoryIdAndUserId(Long categoryId, Long userId);
}
