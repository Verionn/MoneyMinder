package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchasedItemService {
    List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId);

    PurchasedItemNameListResponse getPurchasedItemNamesByPrefix(String prefix);
}
