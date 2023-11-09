package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.list.dto.ListResponse;
import com.minder.MoneyMinder.list.dto.ListsResponse;
import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.OK;

public class UpdateListTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should update list and return 200")
    public void shouldUpdateListAndReturnOk(){
        //given
        var createListResponse = createList(FIRST_LIST_NAME);
        var createdListId = createListResponse.listId();
        var updateListRequestBody = new UpdateListRequestBody(NEW_LIST_NAME, NEW_FULL_PRICE);
        HttpEntity<UpdateListRequestBody> requestEntity = new HttpEntity<>(updateListRequestBody);

        //when
        var updateListResponse = client.exchange(listPath(createdListId), PUT, requestEntity, ListResponse.class);

        //then
        assertThat(updateListResponse.getBody().name(), equalTo(NEW_LIST_NAME));
        assertThat(updateListResponse.getBody().fullPrice(), equalTo(NEW_FULL_PRICE));
        assertThat(updateListResponse.getStatusCode(), equalTo(OK));
    }
}
