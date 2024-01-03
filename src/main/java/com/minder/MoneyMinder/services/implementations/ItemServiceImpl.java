package com.minder.MoneyMinder.services.implementations;


import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemRecord;
import com.minder.MoneyMinder.controllers.purchasedItem.dto.PurchasedItemResponse;
import com.minder.MoneyMinder.repositories.ItemRepository;
import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.repositories.ListRepository;
import com.minder.MoneyMinder.models.ItemEntity;
import com.minder.MoneyMinder.repositories.PurchasedItemRepository;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.mappers.PurchasedItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ListRepository listRepository;
    private final PurchasedItemMapper purchasedItemMapper = PurchasedItemMapper.INSTANCE;
    private final PurchasedItemRepository purchasedItemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ListRepository listRepository, PurchasedItemRepository purchasedItemRepository) {
        this.itemRepository = itemRepository;
        this.listRepository = listRepository;
        this.purchasedItemRepository = purchasedItemRepository;
    }

    @Override
    public ItemEntity addItem(ItemEntity itemEntity, Long listId, Long userId) {
        itemEntity.setListId(listId);
        itemEntity.setTimeCreated(LocalDateTime.now());
        itemEntity.setUserId(userId);
        return itemRepository.save(itemEntity);
    }

    @Override
    public Optional<ItemEntity> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<ItemEntity> getItemsByListIdAndUserId(Long listId, Long userId) {
        return itemRepository.findAllByListIdAndUserId(listId, userId);
    }

    @Override
    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

    @Override
    public Optional<ItemEntity> updateItem(Long itemId, UpdateItemRequestBody updateItemRequestBody) {
        return itemRepository.findById(itemId)
                .map(itemEntity -> updateItemEntity(itemEntity, updateItemRequestBody))
                .map(itemRepository::save);
    }

    @Override
    public boolean existsById(Long itemId, Long userId) {
        return itemRepository.existsByItemIdAndUserId(itemId, userId);
    }

    @Override
    @Transactional
    public void deleteItemsByListId(Long listId) {
        itemRepository.deleteAllByListId(listId);
    }

    @Override
    public PurchasedItemResponse markItemAsPurchased(PurchasedItemRecord purchasedItemRecord) {
        return purchasedItemMapper.purchasedItemRecordToPurchasedItemResponse(
                purchasedItemRepository.save(purchasedItemMapper.purchasedItemRecordToPurchasedItemEntity(purchasedItemRecord)));
    }

    @Override
    public boolean checkIfItemIsOnTheList(Long itemId, Long listId) {
        return itemRepository.getReferenceById(itemId).getListId().equals(listId);
    }

    private ItemEntity updateItemEntity(ItemEntity itemEntity, UpdateItemRequestBody updateItemRequestBody) {
        itemEntity.setName(updateItemRequestBody.name());
        itemEntity.setCategoryId(updateItemRequestBody.categoryId());
        itemEntity.setPrice(updateItemRequestBody.price());
        itemEntity.setAmount(updateItemRequestBody.amount());
        itemEntity.setWeight(updateItemRequestBody.weight());
        itemEntity.setListId(updateItemRequestBody.listId());
        return itemEntity;
    }
}
