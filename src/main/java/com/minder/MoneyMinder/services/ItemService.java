package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.models.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public ItemEntity addItem(ItemEntity itemEntity, Long listId);

    public Optional<ItemEntity> getItem(Long itemId);

    public List<ItemEntity> getItemsOnSpecificList(Long listId);

    public void deleteItem(Long itemID);

    public ItemEntity updateItem(Long itemId, UpdateItemRequestBody updateItemRequestBody);

    public boolean existsById(Long itemId);

    void deleteItemsByListId(Long listId);
}
