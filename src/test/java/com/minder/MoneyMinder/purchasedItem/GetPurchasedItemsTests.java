package com.minder.MoneyMinder.purchasedItem;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetPurchasedItemsTests extends MoneyMinderApplicationTests {

    @DisplayName("Should return all purchased items and status 200")
    @Test
    public void shouldReturnAllPurchasedItemsAndOk(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var purchasedItem = markItemAsPurchased(addedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByCategoryIdPath(createdCategory.categoryId()), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(1));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).itemId(), equalTo(purchasedItem.itemId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).listId(), equalTo(purchasedItem.listId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).categoryId(), equalTo(purchasedItem.categoryId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).name(), equalTo(purchasedItem.name()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).weight(), equalTo(purchasedItem.weight()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).amount(), equalTo(purchasedItem.amount()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).price(), equalTo(purchasedItem.price()));
    }

    @DisplayName("Should return not found when given wrong categoryId")
    @Test
    public void shouldReturnNotFoundWhenGivenWrongCategoryId(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());
        var purchasedItem = markItemAsPurchased(addedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByCategoryIdPath(WRONG_CATEGORY_ID), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(purchasedItemListResponse.getBody());
    }
}
