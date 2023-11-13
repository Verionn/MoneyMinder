package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.category.dto.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> getSpecificCategory(@PathVariable Long categoryId){
        if(!checkIfCategoryExits(categoryId)) {
            return ResponseEntity.notFound().build();
        }
        return categoryService.getCategory(categoryId).map(listRecord -> ResponseEntity.ok().body(
                categoryMapper.categoryEntityToCategoryResponse(listRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CategoriesResponse> getCategories(){
        return ResponseEntity.ok().body(
                new CategoriesResponse(categoryMapper
                        .listOfCategoryEntityToListOfCategoryResponse(categoryService.getCategories())));
    }

    private boolean checkIfCategoryExits(Long categoryId) {
        return categoryService.existsById(categoryId);
    }
}
