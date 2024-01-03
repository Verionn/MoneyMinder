package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.repositories.ItemRepository;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.PurchasedItemService;
import com.minder.MoneyMinder.services.mappers.PurchasedItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedItemServiceImpl implements PurchasedItemService {
    private final PurchasedItemRepository purchasedItemRepository;
    private final ItemRepository itemRepository;
    private final PurchasedItemMapper purchasedItemMapper = PurchasedItemMapper.INSTANCE;

    public PurchasedItemServiceImpl(PurchasedItemRepository purchasedItemRepository, ItemRepository itemRepository) {
        this.purchasedItemRepository = purchasedItemRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId, Long userId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByCategoryIdAndUserId(categoryId, userId));
    }

    @Override
    public PurchasedItemNameListResponse getPurchasedItemNamesByPrefix(String prefix, Long userId) {
        return new PurchasedItemNameListResponse(purchasedItemMapper.purchasedItemListEntityToPurchasedItemNameListResponse(
                        purchasedItemRepository.findAllByPrefixAndUserId(prefix, userId)));
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsByCategoryIdInLastNDays(Long categoryId, Long days, Long userId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByCategoryIdAndUserIdInLastNDays(categoryId, days, userId));
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsInLastNDays(Long days, Long userId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllInLastNDaysAndByUserId(days, userId));
    }

    @Override
    public List<PurchasedItemResponse> getLastNPurchasedItems(Long amountOfItems, Long userId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByDateBoughtAndUserId(amountOfItems, userId));
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsFromList(Long listId, Long userId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(purchasedItemRepository.findAllByListIdAndUserId(listId, userId));
    }
}
