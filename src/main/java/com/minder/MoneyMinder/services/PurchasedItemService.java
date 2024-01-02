package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;

import java.util.List;

public interface PurchasedItemService {
    List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId);

    PurchasedItemNameListResponse getPurchasedItemNamesByPrefix(String prefix);

    List<PurchasedItemResponse> getPurchasedItemsByCategoryIdInLastNDays(Long categoryId, Long days);

    List<PurchasedItemResponse> getPurchasedItemsInLastNDays(Long days);

    List<PurchasedItemResponse> getLastNPurchasedItems(Long amountOfItems);

    List<PurchasedItemResponse> getPurchasedItemsFromList(Long listId);
}
