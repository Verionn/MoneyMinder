package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.list.dto.ListsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class CreateCategoryTests extends MoneyMinderApplicationTests {
    @Test
    @DisplayName("Should get categories")
    public void shouldGetCategoriesAndReturnOk() {
        //given
        createCategory(FIRST_CATEGORY_NAME);
        createCategory(SECOND_CATEGORY_NAME);

        //when
        var getCategoriesResponse = client.getForEntity(prepareUrl(CATEGORIES_RESOURCE),
                CategoriesResponse.class);

        //then
        assertThat(getCategoriesResponse.getStatusCode(), equalTo(OK));
        assertThat(getCategoriesResponse.getBody(), instanceOf(CategoriesResponse.class));
        assertThat(getCategoriesResponse.getBody().categories().size(), is(not(0)));
        assertNotNull(getCategoriesResponse.getBody());
    }

    @Test
    @DisplayName("Should get specific category")
    public void shouldGetSpecificCategoryAndReturnOk() {
        //given
        var createdCategoryResponse = createCategory(FIRST_CATEGORY_NAME);

        //when
        var getCategoryResponse = client.getForEntity(categoryPath(createdCategoryResponse.categoryId()),
                CategoryResponse.class);

        //then
        assertThat(getCategoryResponse.getStatusCode(), equalTo(OK));
        assertNotNull(getCategoryResponse.getBody());
        assertEquals(FIRST_CATEGORY_NAME, getCategoryResponse.getBody().name());
        assertEquals(createdCategoryResponse.categoryId(), getCategoryResponse.getBody().categoryId());
    }
    @Test
    @DisplayName("Should not get category and return 404 when given wrong id")
    public void ShouldNotGetCategoryAndReturn404() {

        //when
        var getCategoryResponse = client.getForEntity(categoryPath(WRONG_CATEGORY_ID),
                CategoryResponse.class);

        //then
        assertThat(getCategoryResponse.getStatusCode(), equalTo(NOT_FOUND));
    }
}
