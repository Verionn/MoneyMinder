package com.minder.MoneyMinder;

import com.minder.MoneyMinder.item.ShoppingItem;
import com.minder.MoneyMinder.item.ShoppingItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MoneyMinderApplicationTests {

	@Autowired
	protected TestRestTemplate client;

//	protected CategoryResponse createCategory(String categoryName) {
//		//given
//		var createCategoryRequestBody = new CreateCategoryRequestBody(categoryName);
//
//		//when
//		var createCategoryResponse = client.postForEntity(prepareUrl(CATEGORY_RESOURCE),
//				createCategoryRequestBody, CategoryResponse.class);
//
//		//then
//		assertThat(createCategoryResponse.getStatusCode(), equalTo(CREATED));
//		assertThat(createCategoryResponse.getBody(), is(not(nullValue())));
//		assertThat(createCategoryResponse.getBody().name(), is(equalTo(categoryName)));
//		return createCategoryResponse.getBody();
//	}

}
