package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.category.dto.CategoryResponse;
import com.minder.MoneyMinder.category.dto.CreateCategoryRequestBody;
import com.minder.MoneyMinder.category.dto.UpdateCategoryRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequestBody createCategoryRequestBody){
        return ResponseEntity.status(201).body(
                categoryMapper.categoryEntityToCategoryResponse(categoryService.addCategory(categoryMapper.createCategoryRequestBodyToCategoryEntity(createCategoryRequestBody))));
    }
    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        if(!checkIfCategoryExits(categoryId)){
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok().build();
    }
    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                           @RequestBody UpdateCategoryRequestBody updateCategoryRequestBody) {
        if(!checkIfCategoryExits(categoryId)){
            return ResponseEntity.notFound().build();
        }
        if(!checkIfDataIsCorrect(updateCategoryRequestBody)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(200).body(
                categoryMapper.categoryEntityToCategoryResponse(categoryService.updateCategory(categoryId, updateCategoryRequestBody)));
    }

    private boolean checkIfCategoryExits(Long categoryId) {
        return categoryService.existsById(categoryId);
    }
    private boolean checkIfDataIsCorrect(UpdateCategoryRequestBody updateCategoryRequestBody) {
        return !updateCategoryRequestBody.name().isBlank();
    }
}
