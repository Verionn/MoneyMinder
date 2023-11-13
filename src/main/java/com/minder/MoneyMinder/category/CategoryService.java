package com.minder.MoneyMinder.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Optional<CategoryEntity> getCategory(Long categoryId){
        return categoryRepository.findById(categoryId);
    }
    public List<CategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }
    public CategoryEntity addCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }
    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
