package com.minder.MoneyMinder.user;

import com.minder.MoneyMinder.MoneyMinderApplicationTests;
import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.user.dto.LoginRequest;
import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

public class UserControllerTests extends MoneyMinderApplicationTests {
    @Test
    @DisplayName("Should not login and return forbidden when given invalid password")
    public void shouldNotLoginWhenGivenInvalidPassword(){
        //when
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(VALID_USER_EMAIL, INVALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return 403 when given invalid data")
    public void shouldReturnForbiddenWhenGivenInvalidData(){
        //when
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(INVALID_USER_EMAIL, INVALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return 403 when given empty data")
    public void shouldReturnForbiddenWhenGivenEmptyData(){
        //when
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(INVALID_USER_EMAIL, INVALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return bad request when given invalid name")
    public void shouldReturnForbiddenWhenGivenInvalidName(){
        //when
        var registerResponse = client.exchange(prepareUrl(REGISTER_PATH), HttpMethod.POST,
                new HttpEntity<>(new RegisterUserRequest(INVALID_USER_NAME, VALID_USER_EMAIL, VALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(registerResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return bad request when given invalid email")
    public void shouldReturnForbiddenWhenGivenInvalidEmail(){
        //when
        var registerResponse = client.exchange(prepareUrl(REGISTER_PATH), HttpMethod.POST,
                new HttpEntity<>(new RegisterUserRequest(VALID_USER_NAME, INVALID_USER_EMAIL, VALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(registerResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return bad request when given invalid password")
    public void shouldReturnForbiddenWhenGivenInvalidPassword(){
        //when
        var registerResponse = client.exchange(prepareUrl(REGISTER_PATH), HttpMethod.POST,
                new HttpEntity<>(new RegisterUserRequest(VALID_USER_NAME, VALID_USER_EMAIL, INVALID_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(registerResponse.getStatusCode(), is(equalTo(BAD_REQUEST)));
    }

    @Test
    @DisplayName("Should return 200 and ok when trying to login with valid Credentials")
    public void shouldReturnOkWhenGivenValidLoginCredentials(){
        //when
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(REGISTERED_USER_EMAIL, REGISTERED_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(OK)));
        assertThat(loginResponse.getBody(), is(not(nullValue())));
    }

    @Test
    @DisplayName("Should return 200 and ok when trying to register with valid Credentials")
    public void shouldReturnOkWhenGivenValidRegisterCredentials(){
        //when
        var registerResponse = client.exchange(prepareUrl(REGISTER_PATH), HttpMethod.POST,
                new HttpEntity<>(new RegisterUserRequest(VALID_USER_NAME, VALID_USER_PASSWORD, VALID_USER_EMAIL)), LoginResponse.class);

        //then
        assertThat(registerResponse.getStatusCode(), is(equalTo(OK)));
        assertThat(registerResponse.getBody(), is(not(nullValue())));
    }

    @Test
    @DisplayName("Should return 403 Forbidden when running without token")
    public void shouldReturn403WhenWithoutToken() {
        runWithoutToken();

        //when
        var getCategoryResponse = client.getForEntity(categoriesPath(VALID_CATEGORY_ID),
                CategoryResponse.class);

        //then
        assertThat(getCategoryResponse.getStatusCode(), equalTo(FORBIDDEN));
    }

    @Test
    @DisplayName("Should return 409 when trying to register user on already taken email")
    public void shouldReturnConflictWhenTryingRegisterUserOnTakenEmail(){
        var registerResponse = client.exchange(prepareUrl(REGISTER_PATH), HttpMethod.POST,
                new HttpEntity<>(new RegisterUserRequest(VALID_USER_NAME, VALID_USER_PASSWORD, REGISTERED_USER_EMAIL)), LoginResponse.class);

        //then
        assertThat(registerResponse.getStatusCode(), is(equalTo(CONFLICT)));
        assertThat(registerResponse.getBody(), is(nullValue()));
    }

    @Test
    @DisplayName("Should return 401 when trying to login on unregistered email")
    public void shouldReturnUnauthorizedWhenTryingToLoginOnUnregisteredEmail(){
        var loginResponse = client.exchange(prepareUrl(LOGIN_PATH), HttpMethod.POST,
                new HttpEntity<>(new LoginRequest(VALID_USER_EMAIL, REGISTERED_USER_PASSWORD)), LoginResponse.class);

        //then
        assertThat(loginResponse.getStatusCode(), is(equalTo(UNAUTHORIZED)));
        assertThat(loginResponse.getBody(), is(nullValue()));
    }
}
