package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.item.dto.CreateItemRequestBody;
import com.minder.MoneyMinder.controllers.item.dto.ItemResponse;
import com.minder.MoneyMinder.controllers.item.dto.UpdateItemRequestBody;
import com.minder.MoneyMinder.models.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemEntity createItemRequestBodyToItem(CreateItemRequestBody createItemRequestBody);

    ItemEntity updateItemRequestBodyToItem(UpdateItemRequestBody updateItemRequestBody);

    ItemResponse itemToItemResponse(ItemEntity itemEntity);

    List<ItemResponse> itemsToItemResponses(List<ItemEntity> items);
}
