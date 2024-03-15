# UsersApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**changePassword**](UsersApi.md#changePassword) | **PUT** /users/change-password |  |
| [**confirmResetPassword**](UsersApi.md#confirmResetPassword) | **PUT** /users/confirm-reset-password |  |
| [**loginUser**](UsersApi.md#loginUser) | **POST** /users/login |  |
| [**registerUser**](UsersApi.md#registerUser) | **POST** /users/register |  |
| [**resetPassword**](UsersApi.md#resetPassword) | **POST** /users/reset-password |  |


<a name="changePassword"></a>
# **changePassword**
> changePassword(changePasswordRequestBody)



change password for user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsersApi apiInstance = new UsersApi(defaultClient);
    ChangePasswordRequestBody changePasswordRequestBody = new ChangePasswordRequestBody(); // ChangePasswordRequestBody | new password
    try {
      apiInstance.changePassword(changePasswordRequestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#changePassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **changePasswordRequestBody** | [**ChangePasswordRequestBody**](ChangePasswordRequestBody.md)| new password | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | changed password successfully |  * JsonWebToken -  <br>  |
| **400** | Bad request |  -  |
| **404** | User not found |  -  |
| **403** | User couldn&#39;t be authenticated |  -  |

<a name="confirmResetPassword"></a>
# **confirmResetPassword**
> confirmResetPassword(resetPasswordToken, confirmResetPasswordRequestBody)



set a new password for user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsersApi apiInstance = new UsersApi(defaultClient);
    String resetPasswordToken = "62eiredl-ORKa-4930-9831-eec546b91830"; // String | Reset password token
    ConfirmResetPasswordRequestBody confirmResetPasswordRequestBody = new ConfirmResetPasswordRequestBody(); // ConfirmResetPasswordRequestBody | new password
    try {
      apiInstance.confirmResetPassword(resetPasswordToken, confirmResetPasswordRequestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#confirmResetPassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **resetPasswordToken** | **String**| Reset password token | |
| **confirmResetPasswordRequestBody** | [**ConfirmResetPasswordRequestBody**](ConfirmResetPasswordRequestBody.md)| new password | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successfully set a new password for user |  -  |
| **400** | password is invalid |  -  |
| **404** | Token is expired or does not exist |  -  |

<a name="loginUser"></a>
# **loginUser**
> loginUser(loginUserRequestBody)



login user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsersApi apiInstance = new UsersApi(defaultClient);
    LoginUserRequestBody loginUserRequestBody = new LoginUserRequestBody(); // LoginUserRequestBody | User details to login to the account
    try {
      apiInstance.loginUser(loginUserRequestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#loginUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **loginUserRequestBody** | [**LoginUserRequestBody**](LoginUserRequestBody.md)| User details to login to the account | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | logged in successfully |  * JsonWebToken -  <br>  |
| **400** | Bad request |  -  |
| **401** | User with that email does not exists |  -  |
| **403** | User couldn&#39;t be authenticated |  -  |

<a name="registerUser"></a>
# **registerUser**
> registerUser(createUserRequestBody)



Register user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsersApi apiInstance = new UsersApi(defaultClient);
    CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody(); // CreateUserRequestBody | User details to register new user
    try {
      apiInstance.registerUser(createUserRequestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#registerUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **createUserRequestBody** | [**CreateUserRequestBody**](CreateUserRequestBody.md)| User details to register new user | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Successfully registered user |  -  |
| **400** | Bad request |  -  |
| **409** | User already exists |  -  |

<a name="resetPassword"></a>
# **resetPassword**
> resetPassword(resetPasswordRequestBody)



reset password for user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsersApi apiInstance = new UsersApi(defaultClient);
    ResetPasswordRequestBody resetPasswordRequestBody = new ResetPasswordRequestBody(); // ResetPasswordRequestBody | reset password
    try {
      apiInstance.resetPassword(resetPasswordRequestBody);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#resetPassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **resetPasswordRequestBody** | [**ResetPasswordRequestBody**](ResetPasswordRequestBody.md)| reset password | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successfully send message with link to reset password |  -  |
| **400** | Bad request |  -  |
| **404** | User not found |  -  |

