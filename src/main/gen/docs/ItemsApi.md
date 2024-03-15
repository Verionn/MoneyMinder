# ItemsApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addNewItemToSpecificList**](ItemsApi.md#addNewItemToSpecificList) | **POST** /lists/{listId}/items |  |
| [**deleteSpecificItemFromSpecificList**](ItemsApi.md#deleteSpecificItemFromSpecificList) | **DELETE** /lists/{listId}/items/{itemId} |  |
| [**getAllItemsFromSpecificList**](ItemsApi.md#getAllItemsFromSpecificList) | **GET** /lists/{listId}/items |  |
| [**markItemAsPurchased**](ItemsApi.md#markItemAsPurchased) | **POST** /lists/{listId}/items/{itemId}/purchased |  |
| [**returnSpecificItemFromSpecificList**](ItemsApi.md#returnSpecificItemFromSpecificList) | **GET** /lists/{listId}/items/{itemId} |  |
| [**updateSpecificItemFromSpecificList**](ItemsApi.md#updateSpecificItemFromSpecificList) | **PUT** /lists/{listId}/items/{itemId} |  |


<a name="addNewItemToSpecificList"></a>
# **addNewItemToSpecificList**
> Item addNewItemToSpecificList(listId, createItemRequestBody)



Add new item to specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    CreateItemRequestBody createItemRequestBody = new CreateItemRequestBody(); // CreateItemRequestBody | Add new item body
    try {
      Item result = apiInstance.addNewItemToSpecificList(listId, createItemRequestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#addNewItemToSpecificList");
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
| **createItemRequestBody** | [**CreateItemRequestBody**](CreateItemRequestBody.md)| Add new item body | [optional] |

### Return type

[**Item**](Item.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully added new item to specific list |  -  |
| **400** | Wrong new item request body |  -  |
| **404** | List or category not found |  -  |

<a name="deleteSpecificItemFromSpecificList"></a>
# **deleteSpecificItemFromSpecificList**
> deleteSpecificItemFromSpecificList(listId, itemId)



Delete specific item from specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    Integer itemId = 56; // Integer | ID of the item
    try {
      apiInstance.deleteSpecificItemFromSpecificList(listId, itemId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#deleteSpecificItemFromSpecificList");
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
| **itemId** | **Integer**| ID of the item | |

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
| **200** | Successfully deleted item |  -  |
| **404** | List or Item not found |  -  |

<a name="getAllItemsFromSpecificList"></a>
# **getAllItemsFromSpecificList**
> GetAllItemsFromSpecificList200Response getAllItemsFromSpecificList(listId)



Return all items from specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    try {
      GetAllItemsFromSpecificList200Response result = apiInstance.getAllItemsFromSpecificList(listId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#getAllItemsFromSpecificList");
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

[**GetAllItemsFromSpecificList200Response**](GetAllItemsFromSpecificList200Response.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully returned all items from specific list |  -  |
| **404** | List not found |  -  |

<a name="markItemAsPurchased"></a>
# **markItemAsPurchased**
> markItemAsPurchased(listId, itemId)



Mark item as purchased

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    Integer itemId = 56; // Integer | ID of the item
    try {
      apiInstance.markItemAsPurchased(listId, itemId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#markItemAsPurchased");
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
| **itemId** | **Integer**| ID of the item | |

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
| **200** | Successfully purchased item - item has been moved to another table |  -  |
| **404** | List or Item not found |  -  |

<a name="returnSpecificItemFromSpecificList"></a>
# **returnSpecificItemFromSpecificList**
> Item returnSpecificItemFromSpecificList(listId, itemId)



Return specific item from specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    Integer itemId = 56; // Integer | ID of the item
    try {
      Item result = apiInstance.returnSpecificItemFromSpecificList(listId, itemId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#returnSpecificItemFromSpecificList");
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
| **itemId** | **Integer**| ID of the item | |

### Return type

[**Item**](Item.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully returned item |  -  |
| **404** | List or Item not found |  -  |

<a name="updateSpecificItemFromSpecificList"></a>
# **updateSpecificItemFromSpecificList**
> Item updateSpecificItemFromSpecificList(listId, itemId, itemRequestBody)



Update specific item from specific list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ItemsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    ItemsApi apiInstance = new ItemsApi(defaultClient);
    Integer listId = 56; // Integer | ID of the list
    Integer itemId = 56; // Integer | ID of the item
    ItemRequestBody itemRequestBody = new ItemRequestBody(); // ItemRequestBody | Update item body
    try {
      Item result = apiInstance.updateSpecificItemFromSpecificList(listId, itemId, itemRequestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ItemsApi#updateSpecificItemFromSpecificList");
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
| **itemId** | **Integer**| ID of the item | |
| **itemRequestBody** | [**ItemRequestBody**](ItemRequestBody.md)| Update item body | [optional] |

### Return type

[**Item**](Item.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully updated item |  -  |
| **400** | Wrong request body |  -  |
| **404** | List or Item not found |  -  |

