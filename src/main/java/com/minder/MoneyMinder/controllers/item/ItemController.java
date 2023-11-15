package com.minder.MoneyMinder.controllers.item;

import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.mappers.ItemMapper;
import com.minder.MoneyMinder.services.ItemServiceImpl;
import com.minder.MoneyMinder.controllers.item.dto.CreateItemRequestBody;
import com.minder.MoneyMinder.controllers.item.dto.ItemListResponse;
import com.minder.MoneyMinder.controllers.item.dto.ItemResponse;
import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.services.ListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/lists")
public class ItemController {
    private final ItemService itemService;
    private final ListServiceImpl listServiceImpl;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Autowired
    public ItemController(ItemServiceImpl itemService, ListServiceImpl listServiceImpl) {
        this.itemService = itemService;
        this.listServiceImpl = listServiceImpl;
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<ItemListResponse> getItemsFromSpecificList(@PathVariable Long listId) {
        if (!checkIfListExists(listId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ItemListResponse(
                itemMapper.itemsToItemResponses(itemService.getItemsOnSpecificList(listId))));
    }

    @GetMapping("/{listId}/items/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long listId, @PathVariable Long itemId) {
        if (!checkIfListExists(listId)) {
            return ResponseEntity.notFound().build();
        }

        return itemService.getItem(itemId).map(itemRecord -> ResponseEntity.ok().body(
                        itemMapper.itemToItemResponse(itemRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ItemResponse> addItem(@PathVariable Long listId, @RequestBody CreateItemRequestBody createItemRequestBody) {
        if (!checkIfListExists(listId)) {
            return ResponseEntity.notFound().build();
        }

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
        if (!checkIfItemAndListExists(listId, itemId)) {
            return ResponseEntity.notFound().build();
        }

        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long listId,
                                                    @PathVariable Long itemId,
                                                    @RequestBody UpdateItemRequestBody updateItemRequestBody) {

        if (!checkIfItemAndListExists(itemId, listId)) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(updateItemRequestBody.listId())) {
            return ResponseEntity.notFound().build();
        }

        if (checkIfUpdateItemRequestBodyIsInvalid(updateItemRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(itemMapper.itemToItemResponse(itemService.
                updateItem(itemId, updateItemRequestBody)));
    }

    private boolean checkIfNewItemRequestBodyIsInvalid(CreateItemRequestBody createItemRequestBody) {
        return createItemRequestBody.amount() < 0 || createItemRequestBody.name().isBlank() || createItemRequestBody.categoryId() <= 0 || createItemRequestBody.price() < 0 || createItemRequestBody.weight() < 0;
    }

    private boolean checkIfUpdateItemRequestBodyIsInvalid(UpdateItemRequestBody updateItemRequestBody) {
        return updateItemRequestBody.amount() < 0 || updateItemRequestBody.name().isBlank() || updateItemRequestBody.categoryId() <= 0 || updateItemRequestBody.price() < 0 || updateItemRequestBody.weight() < 0;
    }

    private boolean checkIfItemAndListExists(Long itemId, Long listId) {
        return listServiceImpl.existsById(listId) && itemService.existsById(itemId);
    }

    private boolean checkIfListExists(Long listId) {
        return listServiceImpl.existsById(listId);
    }
}
