package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import com.minder.MoneyMinder.models.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Optional<CategoryEntity> getCategory(Long categoryId);

    public List<CategoryEntity> getCategories();

    public CategoryEntity addCategory(CategoryEntity categoryEntity);

    public void deleteCategory(Long categoryId);

    public CategoryEntity updateCategory(Long categoryId, UpdateCategoryRequestBody updateCategoryRequestBody);

    public boolean existsById(Long categoryId);
}
