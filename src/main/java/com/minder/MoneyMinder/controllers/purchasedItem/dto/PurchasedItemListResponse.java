package com.minder.MoneyMinder.controllers.purchasedItem.dto;

import java.util.List;

public record PurchasedItemListResponse(
        List<PurchasedItemResponse> purchasedItems
) {}
