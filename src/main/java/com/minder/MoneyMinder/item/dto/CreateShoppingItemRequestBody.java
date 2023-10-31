package com.minder.MoneyMinder.item.dto;

public record CreateShoppingItemRequestBody(
        String name,
        double price,
        int amount,
        String Category) {
}
