package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.item.dto.UserItemRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MarkItemAsBoughtTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should mark item as bought and return 200")
    public void shouldMarkItemAsBoughtAndReturn200(){

        //given
        var createdList = createList(FIRST_LIST_NAME);
        var addedItem = addItem(FIRST_ITEM_NAME, createdList.listId());

        //when
        var userItemRecordResponse = client.postForEntity(markItemPath(createdList.listId(), addedItem.itemId()), null, UserItemRecord.class);

        //then
        assertThat(userItemRecordResponse.getStatusCode(), equalTo(HttpStatus.OK));

    }
}
