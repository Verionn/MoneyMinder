package com.minder.MoneyMinder.item.dto;

public record ShoppingItemResponse(
        long id,
        Long shoppingListId,
        String name,
        double price,
        int amount,
        String category) {
}
