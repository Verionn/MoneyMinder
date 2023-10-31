package com.minder.MoneyMinder.item;


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
    public void updateShoppingItem(Long shoppingItemID, UpdateShoppingItemRequestBody updateShoppingItemRequestBody) {
        ShoppingItem shoppingItem = shoppingItemRepository.findById(shoppingItemID).
                orElseThrow(() -> new IllegalStateException("Item with id: " + shoppingItemID + " does not exist"));

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

    }
}
