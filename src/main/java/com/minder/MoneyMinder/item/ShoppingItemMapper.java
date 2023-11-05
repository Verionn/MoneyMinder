package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.item.dto.CreateShoppingItemRequestBody;
import com.minder.MoneyMinder.item.dto.ShoppingItemResponse;
import com.minder.MoneyMinder.item.dto.UpdateShoppingItemRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingItemMapper {
    ShoppingItemMapper INSTANCE = Mappers.getMapper(ShoppingItemMapper.class);

    ShoppingItem createShoppingItemRequestBodyToShoppingItem(CreateShoppingItemRequestBody createShoppingItemRequestBody);
    ShoppingItem updateShoppingItemRequestBodyToShoppingItem(UpdateShoppingItemRequestBody updateShoppingItemRequestBody);

    ShoppingItemResponse shoppingItemToShoppingItemResponse(ShoppingItem shoppingItem);

}
