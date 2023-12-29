package com.minder.MoneyMinder.controllers.category;

import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import com.minder.MoneyMinder.models.UserEntity;
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

import java.util.Optional;

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

        if(user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        //user.get().

        if (!checkIfCategoryExits(categoryId)) {
            return ResponseEntity.notFound().build();
        }
        return categoryService.getCategory(categoryId).map(listRecord -> ResponseEntity.ok().body(
                        categoryMapper.categoryEntityToCategoryResponse(listRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CategoriesResponse> getCategories() {
        return ResponseEntity.ok().body(
                new CategoriesResponse(categoryMapper
                        .listOfCategoryEntityToListOfCategoryResponse(categoryService.getCategories())));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequestBody createCategoryRequestBody) {

        var user = getUserByEmailFromSecurityContext();

//        if(user.isEmpty()){
//            return ResponseEntity.badRequest().build();
//        }

        System.out.println("Czy jest blad: " + user.isRight());

        System.out.println("USER_ID: " + user);
        System.out.println("USER_ID: " + user.getLeft().id());
        System.out.println("USER_ID: " + user.getLeft().email());
        System.out.println("USER_ID: " + user.getLeft().name());

        return ResponseEntity.status(201).body(
                categoryMapper.categoryEntityToCategoryResponse(categoryService.addCategory(
                        categoryMapper.createCategoryRequestBodyToCategoryEntity(createCategoryRequestBody, user.getLeft().id()))));
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        if (!checkIfCategoryExits(categoryId)) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                           @RequestBody UpdateCategoryRequestBody updateCategoryRequestBody) {
        if (!checkIfCategoryExits(categoryId)) {
            return ResponseEntity.notFound().build();
        }
        if (!checkIfDataIsCorrect(updateCategoryRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return categoryService.updateCategory(categoryId, updateCategoryRequestBody)
                .map(categoryEntity -> ResponseEntity.ok().body(categoryMapper.categoryEntityToCategoryResponse(categoryEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean checkIfCategoryExits(Long categoryId) {
        return categoryService.existsById(categoryId);
    }

    private boolean checkIfDataIsCorrect(UpdateCategoryRequestBody updateCategoryRequestBody) {
        return !updateCategoryRequestBody.name().isBlank();
    }

    private Either<UserResponse, Integer> getUserByEmailFromSecurityContext() {
        return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
