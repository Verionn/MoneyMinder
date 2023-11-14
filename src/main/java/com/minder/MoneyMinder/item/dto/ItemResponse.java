package com.minder.MoneyMinder.item.dto;

import java.time.LocalDateTime;

public record ItemResponse(
        Long itemId,
        Long listId,
        String name,
        double price,
        int amount,
        Long categoryId,
        long weight,
        LocalDateTime timeCreated) {
}
