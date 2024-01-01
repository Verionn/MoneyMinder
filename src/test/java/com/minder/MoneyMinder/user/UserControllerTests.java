package com.minder.MoneyMinder.user;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.user.dto.LoginRequest;
import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.HttpStatus.*;

public class UserControllerTests extends MoneyMinderApplicationTests {
    @Test
    @DisplayName("Should not login with invalid data")
    public void shouldNotLoginWhenGivenInvalidData(){
        //when
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(INVALID_USER_EMAIL, INVALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(FORBIDDEN)));
    }

    @Test
    @DisplayName("test")
    public void test(){
        runAsUser();
    }

    @Test
    @DisplayName("test")
    public void test2(){
        runAsUser();
    }

    @Test
    @DisplayName("test")
    public void test3(){
        runAsUser();
    }
}
