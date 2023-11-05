package com.minder.MoneyMinder.item;


import com.minder.MoneyMinder.item.dto.CreateShoppingItemRequestBody;
import com.minder.MoneyMinder.item.dto.UpdateShoppingItemRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingItemService {
    private final ShoppingItemRepository shoppingItemRepository;

    @Autowired
    public ShoppingItemService(ShoppingItemRepository shoppingItemRepository) {
        this.shoppingItemRepository = shoppingItemRepository;
    }

    public List<ShoppingItem> getItems(){
        return shoppingItemRepository.findAll();
    }

    public ShoppingItem addShoppingItem(ShoppingItem shoppingItem, Long listId){
        shoppingItem.setShoppingListId(listId);
        return shoppingItemRepository.save(shoppingItem);
    }

    public void deleteShoppingItem(long shoppingItemID) {
        boolean exists = shoppingItemRepository.existsById(shoppingItemID);
        if(!exists){
            throw new IllegalStateException("Item does not exist!");
        }
        shoppingItemRepository.deleteById(shoppingItemID);
    }

    @Transactional
    public ShoppingItem updateShoppingItem(Long shoppingItemId, UpdateShoppingItemRequestBody updateShoppingItemRequestBody) {
        ShoppingItem shoppingItem = shoppingItemRepository.findById(shoppingItemId).
                orElseThrow(() -> new IllegalStateException("Item with id: " + shoppingItemId + " does not exist"));

        //TODO:
        //sprawdzic czy lista i item istnieje

        double price = updateShoppingItemRequestBody.price();
        int amount = updateShoppingItemRequestBody.amount();

        if(price > 0){
            shoppingItem.setPrice(price);
        }

        if(amount >= 0){
            shoppingItem.setAmount(amount);
        }

        shoppingItem.setName(updateShoppingItemRequestBody.name());
        shoppingItem.setCategory(updateShoppingItemRequestBody.category());
        shoppingItem.setShoppingListId(updateShoppingItemRequestBody.shoppingListId());

        return shoppingItem;
    }

    public List<ShoppingItem> getItemsOnList(Long listId) {
        return shoppingItemRepository.findByShoppingListId(listId);
    }
}
