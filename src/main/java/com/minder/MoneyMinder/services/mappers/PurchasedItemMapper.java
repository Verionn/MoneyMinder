package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.purchasedItem.dto.*;
import com.minder.MoneyMinder.models.ItemEntity;
import com.minder.MoneyMinder.models.PurchasedItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PurchasedItemMapper {
    PurchasedItemMapper INSTANCE = Mappers.getMapper(PurchasedItemMapper.class);

    PurchasedItemRecord itemEntityToPurchasedItemRecord(ItemEntity itemEntity, LocalDateTime timeBought);

    PurchasedItemEntity purchasedItemRecordToPurchasedItemEntity(PurchasedItemRecord purchasedItemRecord);

    PurchasedItemResponse purchasedItemRecordToPurchasedItemResponse(PurchasedItemEntity purchasedItemEntity);

    List<PurchasedItemResponse> purchasedItemListEntityToPurchasedItemListResponse(List<PurchasedItemEntity> purchasedItemsByCategoryId);
    List<PurchasedItemNameResponse> purchasedItemListEntityToPurchasedItemNameListResponse (List<PurchasedItemEntity> purchasedItemEntities);
}
