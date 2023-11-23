package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class DeleteCategoryTests extends MoneyMinderApplicationTests {
    @Test
    @DisplayName("Should delete category and return 200")
    public void shouldDeleteCategoryAndReturn200(){
        //given
        var createCategoryResponse = createCategory(FIRST_CATEGORY_NAME);
        var categoryId = createCategoryResponse.categoryId();
        int numberOfCategories = client.getForEntity(categoriesPath(), CategoriesResponse.class)
                .getBody().categories().size();

        //when
        client.delete(categoriesPath(categoryId));

        //then
        int numberOfCategoriesAfterDelete = client.getForEntity(categoriesPath(), CategoriesResponse.class)
                .getBody().categories().size();

        List<CategoryResponse> categories = client.getForEntity(categoriesPath(), CategoriesResponse.class)
                .getBody().categories();

        assertThat(numberOfCategories, equalTo(numberOfCategoriesAfterDelete + 1));
        assertTrue(categories.stream().map(CategoryResponse::categoryId).noneMatch(id -> id.equals(categoryId)));
    }

    @Test
    @DisplayName("Should return 404 when given wrong category id")
    public void shouldNotDeleteCategoryAndReturnNotFound(){
        //when
        client.delete(categoriesPath(WRONG_CATEGORY_ID));

        //then
        assertThat(client.getForEntity(categoriesPath(WRONG_CATEGORY_ID),
                CategoryResponse.class).getStatusCode(), equalTo(NOT_FOUND));
    }
}
