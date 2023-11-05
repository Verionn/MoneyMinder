package com.minder.MoneyMinder.item.dto;

public record CreateItemRequestBody(
        String name,
        double price,
        int amount,
        String category) {
}
