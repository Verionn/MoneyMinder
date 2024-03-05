package com.minder.MoneyMinder.controllers.item;

import com.minder.MoneyMinder.controllers.item.dto.*;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.services.*;
import com.minder.MoneyMinder.services.implementations.ItemServiceImpl;
import com.minder.MoneyMinder.services.implementations.ListServiceImpl;
import com.minder.MoneyMinder.services.mappers.ItemMapper;
import com.minder.MoneyMinder.services.mappers.PurchasedItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping(path = "/lists")
public class ItemController {
    private final ItemService itemService;
    private final ListService listService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PurchasedItemService purchasedItemService;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final PurchasedItemMapper purchasedItemMapper = PurchasedItemMapper.INSTANCE;

    @Autowired
    public ItemController(ItemServiceImpl itemService, ListServiceImpl listService, UserService userService, CategoryService categoryService, PurchasedItemService purchasedItemService) {
        this.itemService = itemService;
        this.listService = listService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.purchasedItemService = purchasedItemService;
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<ItemListResponse> getItemsFromList(@PathVariable Long listId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfListExists(listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ItemListResponse(
                itemMapper.itemsToItemResponses(itemService.getItemsByListIdAndUserId(listId, userId))));
    }

    @GetMapping("/{listId}/items/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long listId, @PathVariable Long itemId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfItemAndListExists(itemId, listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        if (!itemService.checkIfItemIsOnTheList(itemId, listId)) {
            return ResponseEntity.badRequest().build();
        }

        return itemService.getItem(itemId).map(itemRecord -> ResponseEntity.ok().body(
                        itemMapper.itemToItemResponse(itemRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ItemResponse> addItem(@PathVariable Long listId, @RequestBody CreateItemRequestBody createItemRequestBody) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfListExists(listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExists(createItemRequestBody.categoryId(), userId)) {
            return ResponseEntity.notFound().build();
        }

        if (checkIfNewItemRequestBodyIsInvalid(createItemRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.itemToItemResponse(
                itemService.addItem(itemMapper.
                        createItemRequestBodyToItemEntity(createItemRequestBody), listId, userId))
        );
    }

    @DeleteMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long listId, @PathVariable Long itemId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfItemAndListExists(itemId, listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        if (!itemService.checkIfItemIsOnTheList(itemId, listId)) {
            return ResponseEntity.badRequest().build();
        }

        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{listId}/items/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long listId,
                                                   @PathVariable Long itemId,
                                                   @RequestBody UpdateItemRequestBody updateItemRequestBody) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfItemAndListExists(itemId, listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(updateItemRequestBody.listId(), userId)) {
            return ResponseEntity.notFound().build();
        }

        if (checkIfUpdateItemRequestBodyIsInvalid(updateItemRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        if (!itemService.checkIfItemIsOnTheList(itemId, listId)) {
            return ResponseEntity.badRequest().build();
        }

        return itemService.updateItem(itemId, updateItemRequestBody)
                .map(itemEntity -> ResponseEntity.ok().body(itemMapper.itemToItemResponse(itemEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/{listId}/items/{itemId}/purchased")
    public ResponseEntity<PurchasedItemResponse> markItemAsPurchased(@PathVariable Long listId,
                                                                     @PathVariable Long itemId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }
        var userId = user.getLeft().userId();

        if (!checkIfItemAndListExists(itemId, listId, userId)) {
            return ResponseEntity.notFound().build();
        }

        if (!itemService.checkIfItemIsOnTheList(itemId, listId)) {
            return ResponseEntity.badRequest().build();
        }

        return itemService.getItem(itemId)
                .map(itemEntity -> {
                    itemService.deleteItem(itemId);
                    itemEntity.setUserId(userId);
                    return ResponseEntity.ok().body(itemService.markItemAsPurchased(
                            purchasedItemMapper.itemEntityToPurchasedItemRecord(itemEntity, LocalDateTime.now())));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean checkIfNewItemRequestBodyIsInvalid(CreateItemRequestBody createItemRequestBody) {
        return createItemRequestBody.amount() < 0 || createItemRequestBody.name().isBlank() || createItemRequestBody.categoryId() <= 0 || createItemRequestBody.price() < 0 || createItemRequestBody.weight() < 0;
    }

    private boolean checkIfUpdateItemRequestBodyIsInvalid(UpdateItemRequestBody updateItemRequestBody) {
        return updateItemRequestBody.amount() < 0 || updateItemRequestBody.name().isBlank() || updateItemRequestBody.categoryId() <= 0 || updateItemRequestBody.price() < 0 || updateItemRequestBody.weight() < 0;
    }

    private boolean checkIfItemAndListExists(Long itemId, Long listId, Long userId) {
        return listService.existsByListIdAndUserId(listId, userId) && itemService.existsById(itemId, userId);
    }

    private boolean checkIfListExists(Long listId, Long userId) {
        return listService.existsByListIdAndUserId(listId, userId);
    }

    private boolean checkIfCategoryExists(Long categoryId, Long userId) {
        return categoryService.existsByCategoryIdAndUserId(categoryId, userId);
    }

}
