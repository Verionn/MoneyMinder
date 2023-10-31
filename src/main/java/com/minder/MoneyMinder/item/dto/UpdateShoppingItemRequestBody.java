package com.minder.MoneyMinder.item.dto;
public record UpdateShoppingItemRequestBody(
        String name,
        int shoppingListID,
        double price,
        int amount,
        String category) {
}
