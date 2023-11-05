package com.minder.MoneyMinder.item;


import com.minder.MoneyMinder.item.dto.UpdateItemRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> getItems(){
        return itemRepository.findAll();
    }

    public ItemEntity addItem(ItemEntity itemEntity, Long listId){
        itemEntity.setListId(listId);
        return itemRepository.save(itemEntity);
    }

    public void deleteItem(long itemID) {
        boolean exists = itemRepository.existsById(itemID);
        if(!exists){
            throw new IllegalStateException("Item does not exist!");
        }
        itemRepository.deleteById(itemID);
    }

    @Transactional
    public ItemEntity updateItem(Long itemId, UpdateItemRequestBody updateItemRequestBody) {
        ItemEntity itemEntity = itemRepository.findById(itemId).
                orElseThrow(() -> new IllegalStateException("Item with id: " + itemId + " does not exist"));

        //TODO:
        //sprawdzic czy lista i item istnieje

        double price = updateItemRequestBody.price();
        int amount = updateItemRequestBody.amount();

        if(price > 0){
            itemEntity.setPrice(price);
        }

        if(amount >= 0){
            itemEntity.setAmount(amount);
        }

        itemEntity.setName(updateItemRequestBody.name());
        itemEntity.setCategory(updateItemRequestBody.category());
        itemEntity.setListId(updateItemRequestBody.listId());

        return itemEntity;
    }

    public List<ItemEntity> getItemsOnList(Long listId) {
        return itemRepository.findByListId(listId);
    }
}
