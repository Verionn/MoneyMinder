package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.item.dto.CreateItemRequestBody;
import com.minder.MoneyMinder.item.dto.ItemResponse;
import com.minder.MoneyMinder.item.dto.UpdateItemRequestBody;
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
