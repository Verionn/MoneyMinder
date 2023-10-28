package com.minder.MoneyMinder.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "item")
public class ShoppingItemControler {
    private final ShoppingItemService shoppingItemService;

    @Autowired
    public ShoppingItemControler(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    @GetMapping
    public String getItems(){
        return shoppingItemService.getItems().toString();
    }
}
