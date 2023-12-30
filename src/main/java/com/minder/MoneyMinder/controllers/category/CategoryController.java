package com.minder.MoneyMinder.controllers.category;

import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import com.minder.MoneyMinder.services.CategoryService;
import com.minder.MoneyMinder.services.UserService;
import com.minder.MoneyMinder.services.mappers.CategoryMapper;
import com.minder.MoneyMinder.services.implementations.CategoryServiceImpl;
import com.minder.MoneyMinder.controllers.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.category.dto.CreateCategoryRequestBody;
import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId) {

        var user = getUserByEmailFromSecurityContext();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExits(categoryId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        return categoryService.getCategory(categoryId).map(listRecord -> ResponseEntity.ok().body(
                        categoryMapper.categoryEntityToCategoryResponse(listRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CategoriesResponse> getCategories() {

        var user = getUserByEmailFromSecurityContext();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(
                new CategoriesResponse(categoryMapper
                        .listOfCategoryEntityToListOfCategoryResponse(
                                categoryService.getCategories(user.getLeft().userId()))));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequestBody createCategoryRequestBody) {

        var user = getUserByEmailFromSecurityContext();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if(!checkIfCreateCategoryRequestBodyIsValid(createCategoryRequestBody)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(
                categoryMapper.categoryEntityToCategoryResponse(categoryService.addCategory(
                        categoryMapper.createCategoryRequestBodyToCategoryEntity(
                                createCategoryRequestBody, user.getLeft().userId()))));
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryId") Long categoryId) {

        var user = getUserByEmailFromSecurityContext();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExits(categoryId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                           @RequestBody UpdateCategoryRequestBody updateCategoryRequestBody) {

        var user = getUserByEmailFromSecurityContext();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExits(categoryId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfUpdateCategoryRequestBodyIsValid(updateCategoryRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return categoryService.updateCategory(categoryId, updateCategoryRequestBody)
                .map(categoryEntity -> ResponseEntity.ok().body(categoryMapper.categoryEntityToCategoryResponse(categoryEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean checkIfCategoryExits(Long categoryId, Long userId) {
        return categoryService.existsById(categoryId, userId);
    }

    private boolean checkIfCreateCategoryRequestBodyIsValid(CreateCategoryRequestBody createCategoryRequestBody){
        return !createCategoryRequestBody.name().isBlank();
    }
    private boolean checkIfUpdateCategoryRequestBodyIsValid(UpdateCategoryRequestBody updateCategoryRequestBody) {
        return !updateCategoryRequestBody.name().isBlank();
    }

    private Either<UserResponse, Integer> getUserByEmailFromSecurityContext() {
        return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
