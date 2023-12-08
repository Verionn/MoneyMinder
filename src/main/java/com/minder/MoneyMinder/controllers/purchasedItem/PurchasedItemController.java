package com.minder.MoneyMinder.controllers.purchasedItem;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemListResponse;
import com.minder.MoneyMinder.repositories.CategoryRepository;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.ListService;
import com.minder.MoneyMinder.services.PurchasedItemService;
import com.minder.MoneyMinder.services.mappers.PurchasedItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
public class PurchasedItemController {
    private final PurchasedItemService purchasedItemService;
    private final PurchasedItemRepository purchasedItemRepository;
    private final ListService listService;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;
    private final PurchasedItemMapper purchasedItemMapper = PurchasedItemMapper.INSTANCE;

    @Autowired
    public PurchasedItemController(PurchasedItemService purchasedItemService, PurchasedItemRepository purchasedItemRepository, ListService listService, ItemService itemService, CategoryRepository categoryRepository) {
        this.purchasedItemService = purchasedItemService;
        this.purchasedItemRepository = purchasedItemRepository;
        this.listService = listService;
        this.itemService = itemService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{userId}/purchasedItems/categories/{categoryId}")
    public ResponseEntity<PurchasedItemListResponse> getPurchasedItemsItemsByCategoryId(@PathVariable Long userId,
                                                                                   @PathVariable Long categoryId){
        if(!checkIfCategoryExists(categoryId)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new PurchasedItemListResponse(purchasedItemService
                .getPurchasedItemsByCategoryId(categoryId)));
    }

    private boolean checkIfCategoryExists(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
