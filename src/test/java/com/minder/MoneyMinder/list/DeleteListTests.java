package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.list.dto.ListResponse;
import com.minder.MoneyMinder.list.dto.ListsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class DeleteListTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should delete list and return 200")
    public void shouldDeleteListAndReturn200(){
        //given
        var createListResponse = createList(FIRST_LIST_NAME);
        var listId = createListResponse.listId();
        int numberOfLists = client.getForEntity(listPath(), ListsResponse.class).getBody()
                .lists().size();

        //when
        client.delete(listPath(listId));

        //then
        int numberOfListsAfterDelete = client.getForEntity(listPath(), ListsResponse.class).getBody()
                .lists().size();

        List<ListResponse> lists = client.getForEntity(listPath(), ListsResponse.class).getBody().lists();

        assertThat(numberOfLists, equalTo(numberOfListsAfterDelete + 1));
        assertTrue(lists.stream().map(ListResponse::listId).noneMatch(id -> id.equals(listId)));
    }

    @Test
    @DisplayName("Should return 404 when given wrong category id")
    public void shouldNotDeleteCategory() {
        //when
        client.delete(listPath(WRONG_LIST_ID));

        //then
        assertThat(client.getForEntity(listPath(WRONG_LIST_ID),
                ListsResponse.class).getStatusCode(), equalTo(NOT_FOUND));
    }
}
