package com.minder.MoneyMinder.item.dto;

public record ItemResponse(
        Long itemId,
        Long listId,
        String name,
        double price,
        int amount,
        String category) {
}
