package com.minder.MoneyMinder.item.dto;

public record ItemResponse(
        long id,
        Long listId,
        String name,
        double price,
        int amount,
        String category) {
}
