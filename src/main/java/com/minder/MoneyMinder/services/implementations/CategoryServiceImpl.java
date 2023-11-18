package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.repositories.CategoryRepository;
import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import com.minder.MoneyMinder.models.CategoryEntity;
import com.minder.MoneyMinder.services.CategoryService;
import jakarta.transaction.Transactional;
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

    public List<CategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public Optional<CategoryEntity> updateCategory(Long categoryId, UpdateCategoryRequestBody updateCategoryRequestBody) {
        return categoryRepository.findById(categoryId)
                .map(categoryEntity -> updateCategoryEntity(updateCategoryRequestBody.name(), categoryEntity));
    }

    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    private CategoryEntity updateCategoryEntity(String newCategoryName, CategoryEntity categoryEntity) {
        categoryEntity.setName(newCategoryName);
        return categoryEntity;
    }
}
