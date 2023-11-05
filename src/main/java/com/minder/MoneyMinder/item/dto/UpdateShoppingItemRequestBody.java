package com.minder.MoneyMinder.item.dto;

public record UpdateShoppingItemRequestBody(
        Long shoppingListId,
        String name,
        double price,
        int amount,
        String category) {
}
