package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.item.dto.ItemResponse;
import com.minder.MoneyMinder.item.dto.UpdateItemRequestBody;
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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(createdList.listId(), WRONG_ITEM_ID), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given wrong list id")
    public void shouldNotUpdateWhenGivenWrongListIdAndReturnNotFound(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(NEW_LIST_ID,
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(WRONG_LIST_ID, addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong list id")
    public void shouldNotUpdateWhenGivenWrongNewListId(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(WRONG_NEW_LIST_ID,
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(updateItemResponse.getBody());
    }

    @Test
    @DisplayName("Should not update item when given new wrong name")
    public void shouldNotUpdateWhenGivenWrongNewName(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                WRONG_ITEM_NAME,
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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                WRONG_PRICE,
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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                WRONG_AMOUNT,
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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                NEW_CATEGORY_ID,
                WRONG_WEIGHT);

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

        //given
        var createdList = createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var updateItemRequestBody = new UpdateItemRequestBody(createdList.listId(),
                NEW_ITEM_NAME,
                NEW_PRICE,
                NEW_AMOUNT,
                WRONG_CATEGORY_ID,
                NEW_WEIGHT);

        HttpEntity<UpdateItemRequestBody> requestEntity = new HttpEntity<>(updateItemRequestBody);

        //when
        var updateItemResponse = client.exchange(itemsPath(addedItem.listId(), addedItem.itemId()), PUT, requestEntity, ItemResponse.class);

        //then
        assertThat(updateItemResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(updateItemResponse.getBody());
    }
}
