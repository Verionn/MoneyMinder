package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.item.dto.ItemListResponse;
import com.minder.MoneyMinder.controllers.item.dto.ItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

public class DeleteItemTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should delete specific item from specific list")
    public void shouldDeleteItemFromList(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var firstAddedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        addItem(SECOND_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        //when
        client.delete(itemsPath(createdList.listId(), firstAddedItem.itemId()));

        //then
        int numberOfItemsAfterDelete = client.getForEntity(itemsPath(createdList.listId()),
                ItemListResponse.class).getBody().items().size();

        List<ItemResponse> items = client.getForEntity(itemsPath(createdList.listId()), ItemListResponse.class).getBody().items();

        assertThat(numberOfItemsAfterDelete, equalTo(1));
        assertTrue(items.stream().map(ItemResponse::itemId).noneMatch(id -> id.equals(firstAddedItem.itemId())));
    }

    @Test
    @DisplayName("Should not delete item when given wrong list Id")
    public void shouldNotDeleteItemWhenGivenWrongListId(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        //when
        client.delete(itemsPath(WRONG_LIST_ID, addedItem.itemId()));

        //then
        var itemResponse = client.getForEntity(itemsPath(createdList.listId(),
                addedItem.itemId()), ItemResponse.class);

        assertThat(itemResponse.getStatusCode(), equalTo(OK));
        assertNotNull(itemResponse);
        assertNotNull(itemResponse.getBody());
        assertThat(itemResponse.getBody().name(), equalTo(FIRST_ITEM_NAME));
    }

    @Test
    @DisplayName("Should not delete item when given wrong item Id")
    public void shouldNotDeleteItemWhenGivenWrongItemId(){
        runAsUser();

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        //when
        client.delete(itemsPath(createdList.listId(), WRONG_ITEM_ID));

        //then
        var itemResponse = client.getForEntity(itemsPath(createdList.listId(),
                addedItem.itemId()), ItemResponse.class);

        assertThat(itemResponse.getStatusCode(), equalTo(OK));
        assertNotNull(itemResponse);
        assertNotNull(itemResponse.getBody());
        assertThat(itemResponse.getBody().name(), equalTo(FIRST_ITEM_NAME));
    }
}
