package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;

import java.util.List;

public interface PurchasedItemService {
    List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId, Long userId);

    PurchasedItemNameListResponse getPurchasedItemNamesByPrefix(String prefix, Long userId);

    List<PurchasedItemResponse> getPurchasedItemsByCategoryIdInLastNDays(Long categoryId, Long days, Long userId);

    List<PurchasedItemResponse> getPurchasedItemsInLastNDays(Long days, Long userId);

    List<PurchasedItemResponse> getLastNPurchasedItems(Long amountOfItems, Long userId);

    List<PurchasedItemResponse> getPurchasedItemsFromList(Long listId, Long userId);
}
