package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.list.dto.ListsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

public class CreateListTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should get lists")
    public void shouldGetListsAndReturnOk() {
        //given
        createList(FIRST_LIST_NAME);
        createList(SECOND_LIST_NAME);

        //when
        var getListsResponse = client.getForEntity(prepareUrl(LIST_RESOURCE),
                ListsResponse.class);

        //then
        assertThat(getListsResponse.getStatusCode(), equalTo(OK));
        assertThat(getListsResponse.getBody(), instanceOf(ListsResponse.class));
        assertThat(getListsResponse.getBody().lists().size(), is(not(0)));
        assertThat(getListsResponse.getBody().lists().size(), is(2));
        assertNotNull(getListsResponse.getBody());
    }
}
