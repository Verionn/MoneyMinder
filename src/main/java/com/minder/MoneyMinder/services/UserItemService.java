package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.item.dto.UserItemRecord;

public interface UserItemService {
    void markItemAsBought(UserItemRecord userItemRecord);
}
