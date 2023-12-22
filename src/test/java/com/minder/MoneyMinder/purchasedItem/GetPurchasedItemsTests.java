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

    @DisplayName("Should return all purchased items by category and status 200")
    @Test
    public void shouldReturnAllPurchasedItemsByCategoryIdAndOk(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var secondAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        int amountOfPurchasedItemsByCategoryId = client.getForEntity(
                purchasedItemsByCategoryIdPath(createdCategory.categoryId()), PurchasedItemListResponse.class).getBody().purchasedItems().size();

        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByCategoryIdPath(createdCategory.categoryId()), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemListResponse.getBody());
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(amountOfPurchasedItemsByCategoryId + 2));
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
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByCategoryIdPath(WRONG_CATEGORY_ID), PurchasedItemListResponse.class);

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
        var purchasedItemNameListResponse = client.getForEntity(
                purchasedItemsByPrefixPath(GOOD_PREFIX), PurchasedItemNameListResponse.class);

        //then
        assertThat(purchasedItemNameListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemNameListResponse.getBody());
        assertThat(purchasedItemNameListResponse.getBody().purchasedItemNameResponses().size(), equalTo(1));
        assertThat(purchasedItemNameListResponse.getBody().purchasedItemNameResponses().get(0).name(), equalTo(secondPurchasedItem.name()));
    }

    @DisplayName("Should return all purchased item by category in 2 last days and 200")
    @Test
    public void shouldReturnPurchasedItemsByCategoryInLast2DaysAndOk(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        var secondAddedItem = addItem(SECOND_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        int amountOfPurchasedItemsInLast2Days = client.getForEntity(
                purchasedItemsByCategoryIdAndDaysPath(createdCategory.categoryId(), DAYS), PurchasedItemListResponse.class).getBody().purchasedItems().size();

        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());

        //when
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByCategoryIdAndDaysPath(createdCategory.categoryId(), DAYS), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemListResponse.getBody());
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(amountOfPurchasedItemsInLast2Days + 2));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).name(), equalTo(firstPurchasedItem.name()));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(1).name(), equalTo(secondPurchasedItem.name()));
    }

    @DisplayName("Should return bad request when given wrong number of days when trying to get purchased items by categoryId and days")
    @Test
    public void ShouldReturnBadRequestWhenGivenWrongDaysNumber(){
        //given
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByCategoryIdAndDaysPath(
                createdCategory.categoryId(), WRONG_DAYS), PurchasedItemListResponse.class);

        //
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(purchasedItemListResponse.getBody());
    }

    @DisplayName("Should return not found when given a wrong category")
    @Test
    public void ShouldReturnBadRequestWhenGivenWrongCategory(){

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByCategoryIdAndDaysPath(
                WRONG_CATEGORY_ID, WRONG_DAYS), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertNull(purchasedItemListResponse.getBody());
    }

    @DisplayName("Should return bad request when given a negative number of days when trying to get purchased items in last n days")
    @Test
    public void ShouldReturn400WhenGivenNegativeDaysNumber(){

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByDaysPath(
                WRONG_DAYS), PurchasedItemListResponse.class);

        //
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(purchasedItemListResponse.getBody());
    }

    @DisplayName("Should return all purchased item in 2 last days and 200")
    @Test
    public void shouldReturnPurchasedItemsInLast2DaysAndOk(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdFirstCategory = createCategory(FIRST_CATEGORY_NAME);
        var createdSecondCategory = createCategory(SECOND_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdFirstCategory.categoryId());
        var secondAddedItem = addItem(SECOND_ITEM_NAME, createdList.listId(), createdSecondCategory.categoryId());

        int amountOfPurchasedItemsInLast2Days = client.getForEntity(
                purchasedItemsByDaysPath(DAYS), PurchasedItemListResponse.class).getBody().purchasedItems().size();

        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());


        //when
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByDaysPath(DAYS), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemListResponse.getBody());
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(amountOfPurchasedItemsInLast2Days + 2));
    }

    @DisplayName("Should return bad request when given a wrong amount of items")
    @Test
    public void ShouldReturn400WhenGivenWrongAmountOfItems(){

        //when
        var purchasedItemListResponse = client.getForEntity(purchasedItemsByAmountOfItemsPath(
                WRONG_AMOUNT_OF_ITEMS), PurchasedItemListResponse.class);

        //
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertNull(purchasedItemListResponse.getBody());
    }

    @DisplayName("Should return 200 and last 2 purchased items when given a good amount of items")
    @Test
    public void ShouldReturn200AndItemsWhenGivenGoodAmountOfDays(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdFirstCategory = createCategory(FIRST_CATEGORY_NAME);
        var createdSecondCategory = createCategory(SECOND_CATEGORY_NAME);

        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdFirstCategory.categoryId());
        var secondAddedItem = addItem(SECOND_ITEM_NAME, createdList.listId(), createdSecondCategory.categoryId());

        var firstPurchasedItem = markItemAsPurchased(firstAddedItem.itemId(), createdList.listId());
        var secondPurchasedItem = markItemAsPurchased(secondAddedItem.itemId(), createdList.listId());


        //when
        var purchasedItemListResponse = client.getForEntity(
                purchasedItemsByAmountOfItemsPath(AMOUNT_OF_ITEMS), PurchasedItemListResponse.class);

        //then
        assertThat(purchasedItemListResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertNotNull(purchasedItemListResponse.getBody());
        assertThat(purchasedItemListResponse.getBody().purchasedItems().size(), equalTo(2));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(0).name(), equalTo(SECOND_ITEM_NAME));
        assertThat(purchasedItemListResponse.getBody().purchasedItems().get(1).name(), equalTo(FIRST_ITEM_NAME));
    }
}
