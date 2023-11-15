package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.list.dto.FullPriceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GetFullPriceTests extends MoneyMinderApplicationTests {

    @Test
    @DisplayName("Should return value and 200")
    public void shouldReturnPriceAnd200(){
        //given
        var createdList = createList(FIRST_LIST_NAME);
        addItem(FIRST_ITEM_NAME, createdList.listId());
        addItem(SECOND_ITEM_NAME, createdList.listId());

        //when
        var getFullPriceResponse = client.getForEntity(fullPricePath(createdList.listId()),
                FullPriceResponse.class);

        //then
        assertThat(getFullPriceResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(getFullPriceResponse.getBody().fullPrice(), equalTo( 2*RANDOM_PRICE));
    }

    @Test
    @DisplayName("Should return not found when given bad list id")
    public void shouldReturnNotFoundWhenGivenBadData(){

        //when
        var getFullPriceResponse = client.getForEntity(fullPricePath(WRONG_LIST_ID),
                FullPriceResponse.class);

        //then
        assertThat(getFullPriceResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
