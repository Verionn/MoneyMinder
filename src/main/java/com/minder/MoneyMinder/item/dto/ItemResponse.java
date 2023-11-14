package com.minder.MoneyMinder.item.dto;

import java.time.LocalDateTime;

public record ItemResponse(
        Long itemId,
        Long listId,
        String name,
        Double price,
        Integer amount,
        Long categoryId,
        Long weight,
        LocalDateTime timeCreated) {
}
