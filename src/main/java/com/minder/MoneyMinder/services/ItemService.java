package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemRecord;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.models.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    ItemEntity addItem(ItemEntity itemEntity, Long listId);

    Optional<ItemEntity> getItem(Long itemId);

    List<ItemEntity> getItemsByListId(Long listId);

    void deleteItem(Long itemID);

    Optional<ItemEntity> updateItem(Long itemId, UpdateItemRequestBody updateItemRequestBody);

    boolean existsById(Long itemId);

    void deleteItemsByListId(Long listId);

    PurchasedItemResponse markItemAsPurchased(PurchasedItemRecord purchasedItemRecord);
}
