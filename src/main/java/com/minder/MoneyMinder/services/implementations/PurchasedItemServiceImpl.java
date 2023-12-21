package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemNameListResponse;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.repositories.ItemRepository;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.PurchasedItemService;
import com.minder.MoneyMinder.services.mappers.PurchasedItemMapper;
import org.springframework.http.ResponseEntity;
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
    public List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public PurchasedItemNameListResponse getPurchasedItemNamesByPrefix(String prefix) {
        return new PurchasedItemNameListResponse(purchasedItemMapper.purchasedItemListEntityToPurchasedItemNameListResponse(
                        purchasedItemRepository.findAllByPrefix(prefix)));
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsByCategoryIdInLastNDays(Long categoryId, Long days) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByCategoryIdInLastNDays(categoryId, days));
    }

    @Override
    public List<PurchasedItemResponse> getPurchasedItemsInLastNDays(Long days) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllInLastNDays(days));
    }

    @Override
    public List<PurchasedItemResponse> getLastNPurchasedItems(Long amountOfItems) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(
                purchasedItemRepository.findAllByDateBought(amountOfItems));
    }
}
