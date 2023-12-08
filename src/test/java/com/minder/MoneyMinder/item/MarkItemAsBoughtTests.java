package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.item.dto.ItemListResponse;

import static org.hamcrest.CoreMatchers.not;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MarkItemAsBoughtTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should mark item as bought and return 200")
    public void shouldMarkItemAsBoughtAndReturn200() {

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());

        int numberOfItemsInListBeforeMark = client.getForEntity(itemsPath(createdList.listId()),
                ItemListResponse.class).getBody().items().size();

        //when
        var userItemResponse = client.postForEntity(markItemPath(createdList.listId(), addedItem.itemId()), null, PurchasedItemResponse.class);

        //then
        int numberOfItemsInListAfterMark = client.getForEntity(itemsPath(createdList.listId()),
                ItemListResponse.class).getBody().items().size();

        assertThat(userItemResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(userItemResponse.getBody());
        assertThat(userItemResponse.getBody().name(), equalTo(FIRST_ITEM_NAME));
        assertThat(userItemResponse.getBody().price(), equalTo(RANDOM_PRICE));
        assertThat(userItemResponse.getBody().weight(), equalTo(RANDOM_WEIGHT));
        assertThat(userItemResponse.getBody().amount(), equalTo(RANDOM_AMOUNT));
        assertThat(userItemResponse.getBody().categoryId(), equalTo(RANDOM_CATEGORY_ID));
        assertThat(numberOfItemsInListAfterMark, not(equalTo(numberOfItemsInListBeforeMark)));
    }

    @Test
    @DisplayName("Should return not found when given wrong item id")
    public void shouldReturnNotFoundWhenGivenWrongItemId() {

        //given
        var createdList = createList(FIRST_LIST_NAME);

        //when
        var userItemResponse = client.postForEntity(markItemPath(createdList.listId(), WRONG_ITEM_ID), null, PurchasedItemResponse.class);

        //then
        assertThat(userItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(userItemResponse.getBody());
    }

    @Test
    @DisplayName("Should return not found when given wrong list id")
    public void shouldReturnNotFoundWhenGivenWrongListId() {

        //given

        //when
        var userItemResponse = client.postForEntity(markItemPath(WRONG_LIST_ID, RANDOM_ITEM_ID), null, PurchasedItemResponse.class);

        //then
        assertThat(userItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(userItemResponse.getBody());
    }
}
