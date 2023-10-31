package com.minder.MoneyMinder.item;


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

    public void addShoppingItem(ShoppingItem shoppingItem){
        shoppingItemRepository.save(shoppingItem);
    }

    public void deleteShoppingItem(long shoppingItemID) {
        boolean exists = shoppingItemRepository.existsById(shoppingItemID);
        if(!exists){
            throw new IllegalStateException("Item does not exist!");
        }
        shoppingItemRepository.deleteById(shoppingItemID);
    }

    @Transactional
    public void updateShoppingItem(Long shoppingItemID, Double price, Integer amount) {
        ShoppingItem shoppingItem = shoppingItemRepository.findById(shoppingItemID).
                orElseThrow(() -> new IllegalStateException("Item with id: " + shoppingItemID + " does not exist"));

        if(price > 0 && shoppingItem.getPrice() != price){
            shoppingItem.setPrice(price);
        }

        if(amount != null && amount >= 0 && shoppingItem.getAmount() != amount){
            shoppingItem.setAmount(amount);
        }
    }
}
