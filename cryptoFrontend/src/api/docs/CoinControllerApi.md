# CoinControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**addCoin**](#addcoin) | **POST** /api/coins | |
|[**getCoinCurrent**](#getcoincurrent) | **GET** /api/coins/{id}/current | |
|[**getCoinHistory**](#getcoinhistory) | **GET** /api/coins/{id}/history | Kursverlauf eines Coins abrufen|
|[**getCoins**](#getcoins) | **GET** /api/coins | |
|[**getWatchlist**](#getwatchlist) | **GET** /api/coins/watchlist | |
|[**removeCoin**](#removecoin) | **DELETE** /api/coins | |

# **addCoin**
> Coin addCoin(coin)


### Example

```typescript
import {
    CoinControllerApi,
    Configuration,
    Coin
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

let coin: Coin; //

const { status, data } = await apiInstance.addCoin(
    coin
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **coin** | **Coin**|  | |


### Return type

**Coin**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getCoinCurrent**
> string getCoinCurrent()


### Example

```typescript
import {
    CoinControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

let id: string; //Coin ID (default to undefined)

const { status, data } = await apiInstance.getCoinCurrent(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] | Coin ID | defaults to undefined|


### Return type

**string**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getCoinHistory**
> Array<CoinPriceHistory> getCoinHistory()


### Example

```typescript
import {
    CoinControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

let id: number; //Coin ID (default to undefined)

const { status, data } = await apiInstance.getCoinHistory(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**number**] | Coin ID | defaults to undefined|


### Return type

**Array<CoinPriceHistory>**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Coin history as JSON |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getCoins**
> Array<Coin> getCoins()


### Example

```typescript
import {
    CoinControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

const { status, data } = await apiInstance.getCoins();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**Array<Coin>**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getWatchlist**
> string getWatchlist()


### Example

```typescript
import {
    CoinControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

const { status, data } = await apiInstance.getWatchlist();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**string**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **removeCoin**
> string removeCoin()


### Example

```typescript
import {
    CoinControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CoinControllerApi(configuration);

const { status, data } = await apiInstance.removeCoin();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**string**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

