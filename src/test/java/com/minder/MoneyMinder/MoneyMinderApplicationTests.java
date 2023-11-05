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

@SpringBootTest
@ActiveProfiles("test")
class MoneyMinderApplicationTests {

}
