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
        var createdCategory = createCategory(FIRST_CATEGORY_NAME);
        addItem(FIRST_ITEM_NAME, createdList.listId(), createdCategory.categoryId());
        addItem(SECOND_ITEM_NAME, createdList.listId(), createdCategory.categoryId());

        //when
        var getFullPriceResponse = client.getForEntity(fullPricePath(createdList.listId()),
                FullPriceResponse.class);

        //then
        assertThat(getFullPriceResponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(getFullPriceResponse.getBody().fullPrice(), equalTo( 2* VALID_PRICE));
    }

    @Test
    @DisplayName("Should return not found when given wrong list id")
    public void shouldReturnNotFoundWhenGivenWrongData(){

        //when
        var getFullPriceResponse = client.getForEntity(fullPricePath(WRONG_LIST_ID),
                FullPriceResponse.class);

        //then
        assertThat(getFullPriceResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
