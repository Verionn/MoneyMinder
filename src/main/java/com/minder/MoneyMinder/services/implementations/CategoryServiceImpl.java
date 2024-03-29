package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.repositories.CategoryRepository;
import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import com.minder.MoneyMinder.models.CategoryEntity;
import com.minder.MoneyMinder.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<CategoryEntity> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<CategoryEntity> getCategories(Long userId) {
        return categoryRepository.findAllByUserId(userId);
    }

    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Optional<CategoryEntity> updateCategory(Long categoryId, UpdateCategoryRequestBody updateCategoryRequestBody) {
        return categoryRepository.findById(categoryId)
                .map(categoryEntity -> updateCategoryEntity(updateCategoryRequestBody.name(), categoryEntity))
                .map(categoryRepository::save);
    }

    public boolean existsByCategoryIdAndUserId(Long categoryId, Long userId) {
        return categoryRepository.existsByCategoryIdAndUserId(categoryId, userId);
    }

    private CategoryEntity updateCategoryEntity(String newCategoryName, CategoryEntity categoryEntity) {
        categoryEntity.setName(newCategoryName);
        return categoryEntity;
    }
}
