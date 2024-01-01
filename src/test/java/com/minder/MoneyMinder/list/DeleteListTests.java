package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.list.dto.ListResponse;
import com.minder.MoneyMinder.controllers.list.dto.ListsResponse;
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
        runAsUser();

        //given
        var createListResponse = createList(FIRST_LIST_NAME);
        var listId = createListResponse.listId();
        int numberOfLists = client.getForEntity(listsPath(), ListsResponse.class).getBody()
                .lists().size();

        //when
        client.delete(listsPath(listId));

        //then
        int numberOfListsAfterDelete = client.getForEntity(listsPath(), ListsResponse.class).getBody()
                .lists().size();

        List<ListResponse> lists = client.getForEntity(listsPath(), ListsResponse.class).getBody().lists();

        assertThat(numberOfLists, equalTo(numberOfListsAfterDelete + 1));
        assertTrue(lists.stream().map(ListResponse::listId).noneMatch(id -> id.equals(listId)));
    }

    @Test
    @DisplayName("Should return 404 when given wrong list id")
    public void shouldNotDeleteListAndReturnNotFound() {
        runAsUser();

        //when
        client.delete(listsPath(WRONG_LIST_ID));

        //then
        assertThat(client.getForEntity(listsPath(WRONG_LIST_ID),
                ListsResponse.class).getStatusCode(), equalTo(NOT_FOUND));
    }
}
