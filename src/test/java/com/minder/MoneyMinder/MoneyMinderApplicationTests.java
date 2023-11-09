package com.minder.MoneyMinder;

import com.minder.MoneyMinder.list.dto.CreateListRequestBody;
import com.minder.MoneyMinder.list.dto.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class MoneyMinderApplicationTests {

    private final static String BASE_URL_FORMAT = "http://localhost:%d%s";
    protected static final String LIST_RESOURCE = "/lists";
    public static final String FIRST_LIST_NAME = "DAILY";
    public static final String SECOND_LIST_NAME = "WEEKLY";

    @Autowired
    protected TestRestTemplate client;

    @LocalServerPort
    protected int port;

    protected String prepareUrl(String resource) {
        return String.format(BASE_URL_FORMAT, port, resource);
    }

    protected ListResponse createList(String listName) {
        //given
        var createListRequestBody = new CreateListRequestBody(listName);

        //when
        var createListResponse = client.postForEntity(prepareUrl(LIST_RESOURCE),
                createListRequestBody, ListResponse.class);

        //then
        assertThat(createListResponse.getStatusCode(), equalTo(CREATED));
        assertThat(createListResponse.getBody(), is(not(nullValue())));
        assertThat(createListResponse.getBody().name(), is(equalTo(listName)));
        return createListResponse.getBody();
    }

}
