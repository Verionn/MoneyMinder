package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.item.dto.CreateShoppingItemRequestBody;
import com.minder.MoneyMinder.item.dto.ShoppingItemResponse;
import com.minder.MoneyMinder.item.dto.UpdateShoppingItemRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/lists")
public class ShoppingItemController {
    private final ShoppingItemService shoppingItemService;
    private final ShoppingItemMapper shoppingItemMapper = ShoppingItemMapper.INSTANCE;

    @Autowired
    public ShoppingItemController(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    @GetMapping("/{listId}/items")
    public String getItems(@PathVariable Long listId) {
        List<ShoppingItem> itemsOnList = shoppingItemService.getItemsOnList(listId);
        return itemsOnList.toString();
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ShoppingItemResponse> addShoppingItem(@PathVariable Long listId, @RequestBody CreateShoppingItemRequestBody createShoppingItemRequestBody) {
        //TODO:
        //czy lista istnieje

        if (checkIfNewItemRequestBodyIsInvalid(createShoppingItemRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(shoppingItemMapper.shoppingItemToShoppingItemResponse(
                shoppingItemService.addShoppingItem(shoppingItemMapper.
                        createShoppingItemRequestBodyToShoppingItem(createShoppingItemRequestBody), listId))
        );
    }

    @DeleteMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<HttpStatus> deleteShoppingItem(@PathVariable Long listId, @PathVariable Long itemId) {
        //TODO::
//        if(checkIfListAndItemExists(listId, shoppingItemId)){
//            return ResponseEntity.notFound().build();
//        }
        shoppingItemService.deleteShoppingItem(itemId);
        return ResponseEntity.ok().build();
    }


    @PutMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<ShoppingItemResponse> updateShoppingItems(@PathVariable Long listId, @PathVariable Long itemId,
                                    @RequestBody UpdateShoppingItemRequestBody updateShoppingItemRequestBody) {

        //TODO:
        //weryfikacja czy lista oraz item istnieje

        System.out.println(updateShoppingItemRequestBody);

        return ResponseEntity.ok().body(shoppingItemMapper.shoppingItemToShoppingItemResponse(shoppingItemService.
                updateShoppingItem(itemId, updateShoppingItemRequestBody)));
    }

    private boolean checkIfNewItemRequestBodyIsInvalid(CreateShoppingItemRequestBody createShoppingItemRequestBody) {
        return createShoppingItemRequestBody.amount() < 0 || createShoppingItemRequestBody.name().isBlank() || createShoppingItemRequestBody.category().isBlank() || createShoppingItemRequestBody.price() < 0;
    }

}
