package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.item.dto.ItemResponse;
import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpMethod.PUT;

public class UpdateItemTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should update item and return OK")
    public void shouldUpdateItemAndReturnOK(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(createdList.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(updateItemResponse.getBody());
        assertThat(updateItemResponse.getBody().name(), equalTo(NEW_ITEM_NAME));
        assertThat(updateItemResponse.getBody().price(), equalTo(NEW_PRICE));
        assertThat(updateItemResponse.getBody().amount(), equalTo(NEW_AMOUNT));
        assertThat(updateItemResponse.getBody().weight(), equalTo(NEW_WEIGHT));
        assertThat(updateItemResponse.getBody().categoryId(), equalTo(NEW_CATEGORY_ID));
    }

    @Test
    @DisplayName("Should not update item when given wrong item id")
    public void shouldNotUpdateWhenGivenWrongItemIdAndReturnNotFound(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(createdList.listId(), INVALID_ITEM_ID), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given wrong list id")
    public void shouldNotUpdateWhenGivenWrongListIdAndReturnNotFound(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(NEW_LIST_ID,
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(INVALID_LIST_ID, addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong list id")
    public void shouldNotUpdateWhenGivenWrongNewListId(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(INVALID_NEW_LIST_ID,
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(
                itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong name")
    public void shouldNotUpdateWhenGivenWrongNewName(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                INVALID_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong price")
    public void shouldNotUpdateWhenGivenWrongNewPrice(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                INVALID_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong amount")
    public void shouldNotUpdateWhenGivenWrongNewAmount(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                INVALID_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong weight")
    public void shouldNotUpdateWhenGivenWrongNewWeight(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                INVALID_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong category")
    public void shouldNotUpdateWhenGivenWrongNewCategory(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                INVALID_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }
}
