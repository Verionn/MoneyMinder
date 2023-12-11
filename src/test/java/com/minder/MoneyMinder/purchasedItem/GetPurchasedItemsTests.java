package com.minder.MoneyMinder.purchasedItem;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
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
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var secondAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByCategoryIdPath(createdCategory.categoryId()), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(2));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).itemId(), equalTo(firstPurchasedItem.itemId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).listId(), equalTo(firstPurchasedItem.listId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).categoryId(), equalTo(firstPurchasedItem.categoryId()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).name(), equalTo(firstPurchasedItem.name()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).weight(), equalTo(firstPurchasedItem.weight()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).amount(), equalTo(firstPurchasedItem.amount()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).price(), equalTo(firstPurchasedItem.price()));
    }

    @DisplayName("Should return not found when given wrong categoryId")
    @Test
    public void shouldReturnNotFoundWhenGivenWrongCategoryId(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var purchasedItem = markItemAsPurchased(addedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByCategoryIdPath(WRONG_CATEGORY_ID), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(purchasedItemListResponse.getBody());
    }

    @DisplayName("Should return all item names and 200")
    @Test
    public void shouldReturnItemNamesAndOkWhenGivenGoodPrefix(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var secondAddedItem = addItem(SECOND_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());

        //when
        var purchasedItemNameListResponse = client.getForEntity(purchasedItemsByPrefixPath(GOOD_PREFIX), PurchasedItemNameListResponse.class);

        //then
        assertThat(purchasedItemNameListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemNameListResponse.getBody());
        assertThat(purchasedItemNameListResponse.getBody().purchasedItemNameResponses().size(), equalTo(1));
        assertThat(purchasedItemNameListResponse.getBody().purchasedItemNameResponses().get(0).name(), equalTo(secondPurchasedItem.name()));
    }
}
