package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.item.dto.UserItemRecord;
import com.minder.MoneyMinder.controllers.item.dto.UserItemResponse;

public interface UserItemService {
    UserItemResponse markItemAsBought(UserItemRecord userItemRecord);
}
