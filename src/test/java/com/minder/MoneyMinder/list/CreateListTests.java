package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.list.dto.ListResponse;
import com.minder.MoneyMinder.controllers.list.dto.ListsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class CreateListTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should get lists")
    public void shouldGetListsAndReturnOk() {
        runAsUser();

        //given
        createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);

        //when
        var getListsResponse = client.getForEntity(prepareUrl(LISTS_RESOURCE),
                ListsResponse.class);

        //then
        assertThat(getListsResponse.getStatusCode(), equalTo(OK));
        assertThat(getListsResponse.getBody(), instanceOf(ListsResponse.class));
        assertThat(getListsResponse.getBody().lists().size(), is(not(0)));
        assertNotNull(getListsResponse.getBody());
    }

    @Test
    @DisplayName("Should get specific list")
    public void ShouldGetSpecificListAndReturnOk(){
        runAsUser();

        //given
        var createListResponse = createList(FIRST_LIST_NAME);

        //when
        var getListResponse = client.getForEntity(listsPath(createListResponse.listId()),
                ListResponse.class);

        //then
        assertThat(getListResponse.getStatusCode(), equalTo(OK));
        assertNotNull(getListResponse.getBody());
        assertEquals(FIRST_LIST_NAME, getListResponse.getBody().name());
        assertEquals(LIST_DESCRIPTION, getListResponse.getBody().description());
        assertEquals(createListResponse.listId(), getListResponse.getBody().listId());
    }

    @Test
    @DisplayName("Should not get list and return 404 when given wrong id")
    public void ShouldNotGetListAndReturn404(){
        runAsUser();

        //given

        //when
        var getListResponse = client.getForEntity(listsPath(INVALID_LIST_ID),
                ListResponse.class);

        //then
        assertThat(getListResponse.getStatusCode(), equalTo(NOT_FOUND));
    }
}
