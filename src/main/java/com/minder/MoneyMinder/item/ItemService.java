package com.minder.MoneyMinder.item;


import com.minder.MoneyMinder.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.list.ListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ListRepository listRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ListRepository listRepository) {
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
        itemEntity.setCategory(updateItemRequestBody.category());
        itemEntity.setListId(updateItemRequestBody.listId());

        return itemEntity;
    }

    public boolean existsById(Long itemId) {
        return itemRepository.existsById(itemId);
    }
}
