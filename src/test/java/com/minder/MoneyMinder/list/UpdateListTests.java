package com.minder.MoneyMinder.list;

import com.jayway.jsonpath.internal.function.sequence.First;
import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.list.dto.ListResponse;
import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.*;

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

    @Test
    @DisplayName("Should not update list and return not found when given wrong listId")
    public void shouldNotUpdateListAndReturnNotFound(){
        //given
        var listId = WRONG_LIST_ID;
        var updateListRequestBody = new UpdateListRequestBody(NEW_LIST_NAME, NEW_FULL_PRICE);

        HttpEntity<UpdateListRequestBody> requestEntity = new HttpEntity<>(updateListRequestBody);

        //when
        var updateListResponse = client.exchange(listPath(listId), PUT, requestEntity, ListResponse.class);

        //then
        assertThat(updateListResponse.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    @DisplayName("Should not update list and return 400 when given bad data")
    public void shouldNotUpdateListAndReturnBadRequestWhenWrongId(){
        //given
        var createdListResponse = createList(FIRST_LIST_NAME);
        var listId = createdListResponse.listId();
        var updateListRequestBody = new UpdateListRequestBody(NEW_LIST_NAME, WRONG_FULL_PRICE);
        HttpEntity<UpdateListRequestBody> requestEntity = new HttpEntity<>(updateListRequestBody);

        //when
        var updateListResponse = client.exchange(listPath(listId), PUT, requestEntity, UpdateListRequestBody.class);

        //then
        assertThat(updateListResponse.getStatusCode(), equalTo(BAD_REQUEST));
    }

    @Test
    @DisplayName("Should not update list and return 400 when given bad data")
    public void shouldNotUpdateListAndReturnBadRequestWhenEmptyNewName(){
        //given
        var createdListResponse = createList(FIRST_LIST_NAME);
        var listId = createdListResponse.listId();
        var updateListRequestBody = new UpdateListRequestBody(WRONG_LIST_NAME, NEW_FULL_PRICE);
        HttpEntity<UpdateListRequestBody> requestEntity = new HttpEntity<>(updateListRequestBody);

        //when
        var updateListResponse = client.exchange(listPath(listId), PUT, requestEntity, UpdateListRequestBody.class);

        //then
        assertThat(updateListResponse.getStatusCode(), equalTo(BAD_REQUEST));
    }
}
