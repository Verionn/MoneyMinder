package com.minder.MoneyMinder.shoppingList;

import com.minder.MoneyMinder.shoppingList.dto.UpdateShoppingListRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "list")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public List<String> getShoppingList() {
        return shoppingListService.getShoppingList();
    }

    @PostMapping
    public void addShoppingList(@RequestBody ShoppingList shoppingList) {
        shoppingListService.addShoppingList(shoppingList);
    }

    @DeleteMapping(path = "/{shoppingListID}")
    public void deleteShoppingList(@PathVariable("shoppingListID") Long shoppingListID) {
        shoppingListService.deleteShoppingList(shoppingListID);
    }

    @PutMapping(path = "/{shoppingListID}")
    public void updateShoppingLists(@PathVariable("shoppingListID") Long shoppingListID,
                                    @RequestBody UpdateShoppingListRequestBody updateShoppingListRequestBody) {
        shoppingListService.updateShoppingList(shoppingListID, updateShoppingListRequestBody);

    }
}
