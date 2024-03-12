package com.minder.MoneyMinder.controllers.purchasedItem;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.repositories.CategoryRepository;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.ListService;
import com.minder.MoneyMinder.services.PurchasedItemService;
import com.minder.MoneyMinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/purchased-items")
public class PurchasedItemController {
    private final PurchasedItemService purchasedItemService;
    private final PurchasedItemRepository purchasedItemRepository;
    private final UserService userService;
    private final ListService listService;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PurchasedItemController(PurchasedItemService purchasedItemService, PurchasedItemRepository purchasedItemRepository, UserService userService, ListService listService, ItemService itemService, CategoryRepository categoryRepository) {
        this.purchasedItemService = purchasedItemService;
        this.purchasedItemRepository = purchasedItemRepository;
        this.userService = userService;
        this.listService = listService;
        this.itemService = itemService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsByCategoryId(@PathVariable Long categoryId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExists(categoryId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService
                .getPurchasedItemsByCategoryId(categoryId, user.getLeft().userId())));
    }

    @GetMapping("/names/{prefix}")
    public ResponseEntity<PurchasedItemNameListResponse> getPurchasedItemNamesByPrefix(@PathVariable String prefix) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(
                purchasedItemService.getPurchasedItemNamesByPrefix(prefix, user.getLeft().userId()));
    }

    @GetMapping("/categories/{categoryId}/days/{days}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsByCategoryIdInLastNDays(@PathVariable Long categoryId,
                                                                                              @PathVariable Long days) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCategoryExists(categoryId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        if (days <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(
                purchasedItemService.getPurchasedItemsByCategoryIdInLastNDays(categoryId, days, user.getLeft().userId())));
    }

    @GetMapping("/days/{days}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsInLastNDays(@PathVariable Long days) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (days <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(
                purchasedItemService.getPurchasedItemsInLastNDays(days, user.getLeft().userId())));
    }

    @GetMapping("/items/{amountOfItems}")
    public ResponseEntity<PurchasedItemListResponse> getLastNPurchasedItems(@PathVariable Long amountOfItems) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (amountOfItems <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(
                purchasedItemService.getLastNPurchasedItems(amountOfItems, user.getLeft().userId())));
    }

    @GetMapping("/lists/{listId}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsFromList(@PathVariable Long listId) {

        var user = userService.getUserByEmail();
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(listId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(
                purchasedItemService.getPurchasedItemsFromList(listId, user.getLeft().userId())));
    }

    private boolean checkIfCategoryExists(Long categoryId, Long userId) {
        return categoryRepository.existsByCategoryIdAndUserId(categoryId, userId);
    }

    private boolean checkIfListExists(Long listId, Long userId) {
        return listService.existsByListIdAndUserId(listId, userId);
    }
}
