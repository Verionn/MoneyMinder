package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.repositories.ItemRepository;
import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.repositories.ListRepository;
import com.minder.MoneyMinder.models.ItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ListRepository listRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ListRepository listRepository) {
        this.itemRepository = itemRepository;
        this.listRepository = listRepository;
    }

    public ItemEntity addItem(ItemEntity itemEntity, Long listId) {
        itemEntity.setListId(listId);
        itemEntity.setTimeCreated(LocalDateTime.now());
        return itemRepository.save(itemEntity);
    }

    public Optional<ItemEntity> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<ItemEntity> getItemsOnSpecificList(Long listId) {
        return itemRepository.findByListId(listId);
    }

    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

    @Transactional
    public ItemEntity updateItem(Long itemId, UpdateItemRequestBody updateItemRequestBody) {
        ItemEntity itemEntity = itemRepository.findById(itemId).
                orElseThrow();

        itemEntity.setName(updateItemRequestBody.name());
        itemEntity.setCategoryId(updateItemRequestBody.categoryId());
        itemEntity.setPrice(updateItemRequestBody.price());
        itemEntity.setAmount(updateItemRequestBody.amount());
        itemEntity.setWeight(updateItemRequestBody.weight());
        itemEntity.setListId(updateItemRequestBody.listId());

        return itemEntity;
    }

    public boolean existsById(Long itemId) {
        return itemRepository.existsById(itemId);
    }
}
