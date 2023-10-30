package com.minder.MoneyMinder.item;


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
        System.out.println(shoppingItem);
        shoppingItemRepository.save(shoppingItem);
    }
}
