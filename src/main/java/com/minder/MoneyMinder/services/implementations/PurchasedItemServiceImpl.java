package com.minder.MoneyMinder.services.implementations;

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
    public List<PurchasedItemResponse> getPurchasedItemsByCategoryId(Long categoryId) {
        return purchasedItemMapper.purchasedItemListEntityToPurchasedItemListResponse(purchasedItemRepository.findAllByCategoryId(categoryId));
    }
}