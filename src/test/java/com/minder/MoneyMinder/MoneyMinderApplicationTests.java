package com.minder.MoneyMinder;

import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.category.dto.CreateCategoryRequestBody;
import com.minder.MoneyMinder.controllers.item.dto.CreateItemRequestBody;
import com.minder.MoneyMinder.controllers.item.dto.ItemResponse;
import com.minder.MoneyMinder.controllers.list.dto.CreateListRequestBody;
import com.minder.MoneyMinder.controllers.list.dto.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class MoneyMinderApplicationTests {

    private final static String BASE_URL_FORMAT = "http://localhost:%d%s";
    protected static final String LISTS_RESOURCE = "/lists";
    protected static final String CATEGORIES_RESOURCE = "/categories";
    protected static final String LISTS_DETAILS_PATH_FORMAT = LISTS_RESOURCE + "/%d";
    protected static final String CATEGORY_DETAILS_PATH_FORMAT = CATEGORIES_RESOURCE + "/%d";
    protected static final String ITEMS_RESOURCE = LISTS_RESOURCE + "/%d/items";
    protected static final String ITEMS_DETAILS_PATH_FORMAT = LISTS_RESOURCE + "/%d/items/%d";
    protected static final String MARK_ITEM_PATH_FORMAT = LISTS_RESOURCE + "/%d/items/%d/purchased";
    protected static final String FULL_PRICE_PATH_FORMAT = LISTS_RESOURCE + "/%d/fullprice";
    public static final String LIST_DESCRIPTION = "GO TO THE LIDL BCS OF PROMOTIONS";
    public static final String NEW_LIST_DESCRIPTION = "GO TO THE BIEDRONKA BCS DZIK GRAPE IS THERE";
    public static final String FIRST_LIST_NAME = "NEXT WEEK";
    public static final String SECOND_LIST_NAME = "PARTY";
    public static final String NEW_LIST_NAME = "CHRISTMAS";
    public static final String FIRST_ITEM_NAME = "Bread";
    public static final String SECOND_ITEM_NAME = "Pepsi";
    public static final String NEW_ITEM_NAME = "Sprite";
    public static final String WRONG_LIST_NAME = "";
    public static final int WRONG_LIST_ID = -12;
    public static final String FIRST_CATEGORY_NAME = "Food";
    public static final String SECOND_CATEGORY_NAME = "Sweets";
    public static final String NEW_CATEGORY_NAME = "Drinks";
    public static final String WRONG_CATEGORY_NAME = "";
    public static final Long RANDOM_CATEGORY_ID = 1L;
    public static final Long NEW_CATEGORY_ID = 3L;
    public static final Long WRONG_CATEGORY_ID = -1L;
    public static final String WRONG_ITEM_NAME = "";
    public static final Long NEW_LIST_ID = 2L;
    public static final Long WRONG_NEW_LIST_ID = 22L;
    public static final Long WRONG_ITEM_ID = -13L;
    public static final Long RANDOM_ITEM_ID = 13L;
    public static final Double RANDOM_PRICE = 3.50;
    public static final Double NEW_PRICE = 5.50;
    public static final Double WRONG_PRICE = -3.50;
    public static final int RANDOM_AMOUNT = 1;
    public static final int NEW_AMOUNT = 2;
    public static final int WRONG_AMOUNT = -13;
    public static final long RANDOM_WEIGHT = 123;
    public static final long NEW_WEIGHT = 353;
    public static final long WRONG_WEIGHT = -123;
    public static final LocalDateTime RANDOM_DATE = LocalDateTime.parse("2023-10-15T21:15:00");

    @Autowired
    protected TestRestTemplate client;

    @LocalServerPort
    protected int port;


    protected String prepareUrl(String resource) {
        return String.format(BASE_URL_FORMAT, port, resource);
    }

    protected String listsPath(long listId) {
        return prepareUrl(String.format(LISTS_DETAILS_PATH_FORMAT, listId));
    }

    protected String listsPath() {
        return prepareUrl(String.format(LISTS_RESOURCE));
    }

    protected String itemsPath(long listId){
        return prepareUrl(String.format(ITEMS_RESOURCE, listId));
    }

    protected String itemsPath(long listId, long itemId){
        return prepareUrl(String.format(ITEMS_DETAILS_PATH_FORMAT, listId, itemId));
    }
    protected String purchaseItemPath(long listId, long itemId){
        return prepareUrl(String.format(MARK_ITEM_PATH_FORMAT, listId, itemId));
    }

    protected String fullPricePath(long listId){
        return prepareUrl(String.format(FULL_PRICE_PATH_FORMAT, listId));
    }

    protected String categoriesPath(long categoryId) {
        return prepareUrl(String.format(CATEGORY_DETAILS_PATH_FORMAT, categoryId));
    }
    protected String categoriesPath() {
        return prepareUrl(String.format(CATEGORIES_RESOURCE));
    }
    protected ListResponse createList(String listName) {
        //given
        var createListRequestBody = new CreateListRequestBody(listName, LIST_DESCRIPTION);

        //when
        var createListResponse = client.postForEntity(prepareUrl(LISTS_RESOURCE),
                createListRequestBody, ListResponse.class);

        //then
        assertThat(createListResponse.getStatusCode(), equalTo(CREATED));
        assertThat(createListResponse.getBody(), is(not(nullValue())));
        assertThat(createListResponse.getBody().name(), is(equalTo(listName)));
        return createListResponse.getBody();
    }

    protected CreateItemRequestBody createValidItemRequestBody(String name){
        return new CreateItemRequestBody(name, RANDOM_PRICE, RANDOM_AMOUNT, RANDOM_CATEGORY_ID, RANDOM_WEIGHT, RANDOM_DATE);
    }

    protected ItemResponse addItem(String itemName, Long listId){
        //given
        var createdItemRequestBody = createValidItemRequestBody(itemName);

        //when
        var addItemResponse = client.postForEntity(itemsPath(listId), createdItemRequestBody, ItemResponse.class);

        //then
        assertThat(addItemResponse.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(addItemResponse.getBody().name(), equalTo(itemName));
        assertThat(addItemResponse.getBody().price(), equalTo(RANDOM_PRICE));
        assertThat(addItemResponse.getBody().amount(), equalTo(RANDOM_AMOUNT));
        assertThat(addItemResponse.getBody().weight(), equalTo(RANDOM_WEIGHT));
        return addItemResponse.getBody();
    }

    protected CategoryResponse createCategory(String categoryName) {
        //given
        var createCategoryRequestBody = new CreateCategoryRequestBody(categoryName);

        //when
        var createCategoryResponse = client.postForEntity(prepareUrl(CATEGORIES_RESOURCE),
                createCategoryRequestBody, CategoryResponse.class);

        //then
        assertThat(createCategoryResponse.getStatusCode(), equalTo(CREATED));
        assertThat(createCategoryResponse.getBody(), is(not(nullValue())));
        assertThat(createCategoryResponse.getBody().name(), is(equalTo(categoryName)));
        return createCategoryResponse.getBody();
    }
}
