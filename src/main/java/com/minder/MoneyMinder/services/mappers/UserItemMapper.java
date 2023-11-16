package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.item.dto.UserItemRecord;
import com.minder.MoneyMinder.models.ItemEntity;
import com.minder.MoneyMinder.models.UserItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface UserItemMapper {
    UserItemMapper INSTANCE = Mappers.getMapper(UserItemMapper.class);

    UserItemRecord itemEntityToUserItemRecord(ItemEntity itemEntity);

    UserItemEntity userItemRecordToUserItemEntity(UserItemRecord userItemRecord);
}
