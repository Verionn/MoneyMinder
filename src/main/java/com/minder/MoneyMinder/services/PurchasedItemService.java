package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;

import java.util.List;

public interface PurchasedItemService {
    List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId);
}
