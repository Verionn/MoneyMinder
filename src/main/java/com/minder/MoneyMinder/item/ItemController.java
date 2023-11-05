package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.item.dto.CreateItemRequestBody;
import com.minder.MoneyMinder.item.dto.ItemResponse;
import com.minder.MoneyMinder.item.dto.UpdateItemRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/lists")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{listId}/items")
    public String getItems(@PathVariable Long listId) {
        List<ItemEntity> itemsOnList = itemService.getItemsOnList(listId);
        return itemsOnList.toString();
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ItemResponse> addItem(@PathVariable Long listId, @RequestBody CreateItemRequestBody createItemRequestBody) {
        //TODO:
        //czy lista istnieje

        if (checkIfNewItemRequestBodyIsInvalid(createItemRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(itemMapper.itemToItemResponse(
                itemService.addItem(itemMapper.
                        createItemRequestBodyToItem(createItemRequestBody), listId))
        );
    }

    @DeleteMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long listId, @PathVariable Long itemId) {
        //TODO::
//        if(checkIfListAndItemExists(listId, shoppingItemId)){
//            return ResponseEntity.notFound().build();
//        }
        itemService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }


    @PutMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<ItemResponse> updateItems(@PathVariable Long listId, @PathVariable Long itemId,
                                                    @RequestBody UpdateItemRequestBody updateItemRequestBody) {

        //TODO:
        //weryfikacja czy lista oraz item istnieje

        System.out.println(updateItemRequestBody);

        return ResponseEntity.ok().body(itemMapper.itemToItemResponse(itemService.
                updateItem(itemId, updateItemRequestBody)));
    }

    private boolean checkIfNewItemRequestBodyIsInvalid(CreateItemRequestBody createItemRequestBody) {
        return createItemRequestBody.amount() < 0 || createItemRequestBody.name().isBlank() || createItemRequestBody.category().isBlank() || createItemRequestBody.price() < 0;
    }

}
