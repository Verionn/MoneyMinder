package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.item.dto.UpdateShoppingItemRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;

@RestController
@RequestMapping(path = "item")
public class ShoppingItemController {
    private final ShoppingItemService shoppingItemService;

    @Autowired
    public ShoppingItemController(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    @GetMapping
    public String getItems() {
        return shoppingItemService.getItems().toString();
    }

    @PostMapping
    public void addShoppingItem(@RequestBody ShoppingItem shoppingItem) {
        shoppingItemService.addShoppingItem(shoppingItem);
    }

    @DeleteMapping(path = "/{shoppingItemID}")
    public void deleteShoppingItem(@PathVariable("shoppingItemID") Long shoppingItemID) {
        shoppingItemService.deleteShoppingItem(shoppingItemID);
    }

    @PutMapping(path = "/{shoppingItemID}")
    public void updateShoppingItems(@PathVariable("shoppingItemID") Long shoppingItemID,
                                    @RequestBody UpdateShoppingItemRequestBody updateShoppingItemRequestBody) {
        shoppingItemService.updateShoppingItem(shoppingItemID, updateShoppingItemRequestBody);

    }
}
