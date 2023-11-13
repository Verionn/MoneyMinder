package com.minder.MoneyMinder.item.dto;

public record UpdateItemRequestBody(
        Long listId,
        String name,
        double price,
        int amount,
        Long categoryId,
        long weight) {
}
