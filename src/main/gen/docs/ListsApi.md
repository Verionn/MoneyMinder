# ListsApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNewList**](ListsApi.md#createNewList) | **POST** /lists |  |
| [**deleteSpecificList**](ListsApi.md#deleteSpecificList) | **DELETE** /lists/{listId} |  |
| [**getAllLists**](ListsApi.md#getAllLists) | **GET** /lists |  |
| [**getSpecificList**](ListsApi.md#getSpecificList) | **GET** /lists/{listId} |  |
| [**updateSpecificList**](ListsApi.md#updateSpecificList) | **PUT** /lists/{listId} |  |


<a name="createNewList"></a>
# **createNewList**
> ModelList createNewList()



Create new list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ListsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ListsApi apiInstance = new ListsApi(defaultClient);
    try {
      ModelList result = apiInstance.createNewList();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ListsApi#createNewList");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ModelList**](ModelList.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully created new list |  -  |
| **400** | Bad request body |  -  |

<a name="deleteSpecificList"></a>
# **deleteSpecificList**
> deleteSpecificList(listId)



Delete specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ListsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ListsApi apiInstance = new ListsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    try {
      apiInstance.deleteSpecificList(listId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ListsApi#deleteSpecificList");
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
| **listId** | **Integer**| ID of the list | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully deleted specific list |  -  |
| **404** | List not found |  -  |

<a name="getAllLists"></a>
# **getAllLists**
> GetAllLists200Response getAllLists()



Get all lists

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ListsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ListsApi apiInstance = new ListsApi(defaultClient);
    try {
      GetAllLists200Response result = apiInstance.getAllLists();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ListsApi#getAllLists");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllLists200Response**](GetAllLists200Response.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returned all lists |  -  |

<a name="getSpecificList"></a>
# **getSpecificList**
> ModelList getSpecificList(listId)



Return specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ListsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ListsApi apiInstance = new ListsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    try {
      ModelList result = apiInstance.getSpecificList(listId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ListsApi#getSpecificList");
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
| **listId** | **Integer**| ID of the list | |

### Return type

[**ModelList**](ModelList.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully returned specific list |  -  |
| **404** | List not found |  -  |

<a name="updateSpecificList"></a>
# **updateSpecificList**
> ModelList updateSpecificList(listId, listRequestBody)



Update specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ListsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ListsApi apiInstance = new ListsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    ListRequestBody listRequestBody = new ListRequestBody(); // ListRequestBody | Update list body
    try {
      ModelList result = apiInstance.updateSpecificList(listId, listRequestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ListsApi#updateSpecificList");
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
| **listId** | **Integer**| ID of the list | |
| **listRequestBody** | [**ListRequestBody**](ListRequestBody.md)| Update list body | [optional] |

### Return type

[**ModelList**](ModelList.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully updated specific list |  -  |
| **400** | Wrong request body |  -  |
| **404** | List not found |  -  |

