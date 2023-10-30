package com.minder.MoneyMinder.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "item")
public class ShoppingItemController {
    private final ShoppingItemService shoppingItemService;

    @Autowired
    public ShoppingItemController(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    @GetMapping
    public String getItems(){
        return shoppingItemService.getItems().toString();
    }

    @PostMapping
    public void addShoppingItem(@RequestBody ShoppingItem shoppingItem){
        shoppingItemService.addShoppingItem(shoppingItem);
    }
}
