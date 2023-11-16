package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.controllers.item.dto.UserItemRecord;
import com.minder.MoneyMinder.repositories.ItemRepository;
import com.minder.MoneyMinder.repositories.UserItemRepository;
import com.minder.MoneyMinder.services.UserItemService;
import com.minder.MoneyMinder.services.mappers.UserItemMapper;
import org.springframework.stereotype.Service;

@Service
public class UserItemServiceImpl implements UserItemService {
    private final UserItemRepository userItemRepository;
    private final ItemRepository itemRepository;
    private final UserItemMapper userItemMapper = UserItemMapper.INSTANCE;

    public UserItemServiceImpl(UserItemRepository userItemRepository, ItemRepository itemRepository) {
        this.userItemRepository = userItemRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void markItemAsBought(UserItemRecord userItemRecord) {
        userItemRepository.save(userItemMapper.userItemRecordToUserItemEntity(userItemRecord));
    }
}
