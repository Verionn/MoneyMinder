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

public class MarkItemAsPurchasedTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should mark item as purchased and return 200")
    public void shouldMarkItemAsPurchasedAndReturn200() {

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());

        int numberOfItemsInListBeforeMark = client.getForEntity(itemsPath(createdList.listId()),
                ItemListResponse.class).getBody().items().size();

        //when
        var purchaseItemResponse = client.postForEntity(purchaseItemPath(
                createdList.listId(), addedItem.itemId()), null, PurchasedItemResponse.class);

        //then
        int numberOfItemsInListAfterMark = client.getForEntity(itemsPath(createdList.listId()),
                ItemListResponse.class).getBody().items().size();

        assertThat(purchaseItemResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchaseItemResponse.getBody());
        assertThat(purchaseItemResponse.getBody().name(), equalTo(FIRST_ITEM_NAME));
        assertThat(purchaseItemResponse.getBody().price(), equalTo(RANDOM_PRICE));
        assertThat(purchaseItemResponse.getBody().weight(), equalTo(RANDOM_WEIGHT));
        assertThat(purchaseItemResponse.getBody().amount(), equalTo(RANDOM_AMOUNT));
        assertThat(purchaseItemResponse.getBody().categoryId(), equalTo(RANDOM_CATEGORY_ID));
        assertThat(numberOfItemsInListAfterMark, not(equalTo(numberOfItemsInListBeforeMark)));
    }

    @Test
    @DisplayName("Should return not found when given wrong item id")
    public void shouldReturnNotFoundWhenGivenWrongItemId() {

        //given
        var createdList = createList(FIRST_LIST_NAME);

        //when
        var purchasedItemResponse = client.postForEntity(purchaseItemPath(createdList.listId(), WRONG_ITEM_ID), null, PurchasedItemResponse.class);

        //then
        assertThat(purchasedItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(purchasedItemResponse.getBody());
    }

    @Test
    @DisplayName("Should return not found when given wrong list id")
    public void shouldReturnNotFoundWhenGivenWrongListId() {

        //given

        //when
        var purchasedItemResponse = client.postForEntity(purchaseItemPath(WRONG_LIST_ID, RANDOM_ITEM_ID), null, PurchasedItemResponse.class);

        //then
        assertThat(purchasedItemResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(purchasedItemResponse.getBody());
    }
}
