package com.minder.MoneyMinder.controllers.purchasedItem;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.repositories.CategoryRepository;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.ListService;
import com.minder.MoneyMinder.services.PurchasedItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/purchasedItems")
public class PurchasedItemController {
    private final PurchasedItemService purchasedItemService;
    private final PurchasedItemRepository purchasedItemRepository;
    private final ListService listService;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PurchasedItemController(PurchasedItemService purchasedItemService, PurchasedItemRepository purchasedItemRepository, ListService listService, ItemService itemService, CategoryRepository categoryRepository) {
        this.purchasedItemService = purchasedItemService;
        this.purchasedItemRepository = purchasedItemRepository;
        this.listService = listService;
        this.itemService = itemService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsByCategoryId(@PathVariable Long categoryId){
        if(!checkIfCategoryExists(categoryId)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService
                .getPurchasedItemsByCategoryId(categoryId)));
    }

    @GetMapping("/names/{prefix}")
    public ResponseEntity<PurchasedItemNameListResponse> getPurchasedItemNamesByPrefix(@PathVariable String prefix){
        return ResponseEntity.ok().body(
                purchasedItemService.getPurchasedItemNamesByPrefix(prefix));
    }

    @GetMapping("/categories/{categoryId}/days/{days}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsByCategoryIdInLastNDays(@PathVariable Long categoryId,
                                                                                                @PathVariable Long days){
        if(!checkIfCategoryExists(categoryId)){
            return ResponseEntity.notFound().build();
        }

        if(days <= 0){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(
                purchasedItemService.getPurchasedItemsByCategoryIdInLastNDays(categoryId, days)));
    }

    @GetMapping("/days/{days}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsInLastNDays(@PathVariable Long days){
        if(days <= 0){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService.getPurchasedItemsInLastNDays(days)));
    }

    @GetMapping("/items/{amountOfItems}")
    public ResponseEntity<PurchasedItemListResponse> getLastNPurchasedItems(@PathVariable Long amountOfItems){
        if(amountOfItems <= 0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService.getLastNPurchasedItems(amountOfItems)));
    }

    @GetMapping("/lists/{listId}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsFromList(@PathVariable Long listId){
        if(!checkIfListExists(listId)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService.getPurchasedItemsFromList(listId)));
    }

    private boolean checkIfCategoryExists(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    private boolean checkIfListExists(Long listId) {
        return listService.existsById(listId);
    }
}
