# PurchasedItemsApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getLastNPurchasedItems**](PurchasedItemsApi.md#getLastNPurchasedItems) | **GET** /purchased-items/items/{amountOfItems} |  |
| [**getLastPurchasedItemsFromList**](PurchasedItemsApi.md#getLastPurchasedItemsFromList) | **GET** /purchased-items/lists/{listId} |  |
| [**getPurchasedItemsByCategoryId**](PurchasedItemsApi.md#getPurchasedItemsByCategoryId) | **GET** /purchased-items/categories/{categoryId} |  |
| [**getPurchasedItemsInNDays**](PurchasedItemsApi.md#getPurchasedItemsInNDays) | **GET** /purchased-items/days/{days} |  |
| [**getPurchasedItemsInNDaysByCategory**](PurchasedItemsApi.md#getPurchasedItemsInNDaysByCategory) | **GET** /purchased-items/categories/{categoryId}/days/{days} |  |
| [**getPurchasedItemsNamesByPrefix**](PurchasedItemsApi.md#getPurchasedItemsNamesByPrefix) | **GET** /purchased-items/names/{prefix} |  |


<a name="getLastNPurchasedItems"></a>
# **getLastNPurchasedItems**
> getLastNPurchasedItems(amountOfItems)



Get last N purchased items

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    Integer amountOfItems = 56; // Integer | amount of items
    try {
      apiInstance.getLastNPurchasedItems(amountOfItems);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getLastNPurchasedItems");
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
| **amountOfItems** | **Integer**| amount of items | |

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
| **200** | Successfully returned items |  -  |
| **400** | Wrong amount of items |  -  |

<a name="getLastPurchasedItemsFromList"></a>
# **getLastPurchasedItemsFromList**
> getLastPurchasedItemsFromList(listId)



Return purchased items from list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    Integer listId = 56; // Integer | List id
    try {
      apiInstance.getLastPurchasedItemsFromList(listId);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getLastPurchasedItemsFromList");
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
| **listId** | **Integer**| List id | |

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
| **200** | Successfully returned items |  -  |
| **400** | Wrong list id |  -  |

<a name="getPurchasedItemsByCategoryId"></a>
# **getPurchasedItemsByCategoryId**
> getPurchasedItemsByCategoryId(categoryId)



Get purchased items by categoryId

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    Integer categoryId = 56; // Integer | ID of the category
    try {
      apiInstance.getPurchasedItemsByCategoryId(categoryId);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getPurchasedItemsByCategoryId");
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
| **categoryId** | **Integer**| ID of the category | |

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
| **200** | Successfully returned purchased items |  -  |
| **404** | Category not found |  -  |

<a name="getPurchasedItemsInNDays"></a>
# **getPurchasedItemsInNDays**
> getPurchasedItemsInNDays(days)



Get purchased items in N Days

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    Integer days = 56; // Integer | amount od days
    try {
      apiInstance.getPurchasedItemsInNDays(days);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getPurchasedItemsInNDays");
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
| **days** | **Integer**| amount od days | |

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
| **200** | Successfully returned names |  -  |
| **400** | Wrong amount of days |  -  |

<a name="getPurchasedItemsInNDaysByCategory"></a>
# **getPurchasedItemsInNDaysByCategory**
> getPurchasedItemsInNDaysByCategory(categoryId, days)



Get purchased items in last N days from category

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    Integer categoryId = 56; // Integer | ID of the category
    Integer days = 56; // Integer | amount of days
    try {
      apiInstance.getPurchasedItemsInNDaysByCategory(categoryId, days);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getPurchasedItemsInNDaysByCategory");
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
| **categoryId** | **Integer**| ID of the category | |
| **days** | **Integer**| amount of days | |

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
| **200** | Successfully returned purchased items from n Days |  -  |
| **404** | Category not found |  -  |
| **400** | Wrong amount of days |  -  |

<a name="getPurchasedItemsNamesByPrefix"></a>
# **getPurchasedItemsNamesByPrefix**
> getPurchasedItemsNamesByPrefix(prefix)



Get purchased items names by prefix

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PurchasedItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    PurchasedItemsApi apiInstance = new PurchasedItemsApi(defaultClient);
    String prefix = "prefix_example"; // String | prefix
    try {
      apiInstance.getPurchasedItemsNamesByPrefix(prefix);
    } catch (ApiException e) {
      System.err.println("Exception when calling PurchasedItemsApi#getPurchasedItemsNamesByPrefix");
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
| **prefix** | **String**| prefix | |

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
| **200** | Successfully returned names |  -  |

