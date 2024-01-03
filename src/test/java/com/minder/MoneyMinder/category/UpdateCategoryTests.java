package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.category.dto.UpdateCategoryRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.*;

public class UpdateCategoryTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should update category and return 200")
    public void shouldUpdateCategoryAndReturnOk(){
        runAsUser();

        //given
        var createCategoryResponse = createCategory(FIRST_CATEGORY_NAME);
        var createdCategoryId = createCategoryResponse.categoryId();
        var updateCategoryRequestBody = new UpdateCategoryRequestBody(NEW_CATEGORY_NAME);
        HttpEntity<UpdateCategoryRequestBody> requestEntity = new HttpEntity<>(updateCategoryRequestBody);

        //when
        var updateCategoryResponse = client.exchange(categoriesPath(createdCategoryId), PUT, requestEntity, CategoryResponse.class);

        //then
        assertThat(updateCategoryResponse.getBody().name(), equalTo(NEW_CATEGORY_NAME));
        assertThat(updateCategoryResponse.getStatusCode(), equalTo(OK));
    }

    @Test
    @DisplayName("Should not update category and return not found when given wrong categoryId")
    public void shouldNotUpdateCategoryAndReturnNotFound(){
        runAsUser();

        //given
        var updateCategoryRequestBody = new UpdateCategoryRequestBody(NEW_CATEGORY_NAME);

        HttpEntity<UpdateCategoryRequestBody> requestEntity = new HttpEntity<>(updateCategoryRequestBody);

        //when
        var updateCategoryResponse = client.exchange(categoriesPath(INVALID_CATEGORY_ID), PUT, requestEntity, CategoryResponse.class);

        //then
        assertThat(updateCategoryResponse.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    @DisplayName("Should not update category and return 400 when given bad data")
    public void shouldNotUpdateCategoryAndReturnBadRequestWhenEmptyNewName(){
        runAsUser();

        //given
        var createdCategoryResponse = createCategory(FIRST_CATEGORY_NAME);
        var categoryId = createdCategoryResponse.categoryId();
        var updateCategoryRequestBody = new UpdateCategoryRequestBody(INVALID_CATEGORY_NAME);
        HttpEntity<UpdateCategoryRequestBody> requestEntity = new HttpEntity<>(updateCategoryRequestBody);

        //when
        var updateCategoryResponse = client.exchange(categoriesPath(categoryId), PUT, requestEntity, UpdateCategoryRequestBody.class);

        //then
        assertThat(updateCategoryResponse.getStatusCode(), equalTo(BAD_REQUEST));
    }
}
