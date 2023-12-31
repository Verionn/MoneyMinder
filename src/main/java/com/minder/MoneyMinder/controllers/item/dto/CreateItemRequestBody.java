package com.minder.MoneyMinder.controllers.item.dto;

import java.time.LocalDateTime;

public record CreateItemRequestBody(
        String name,
        Double price,
        Integer amount,
        Long categoryId,
        Long weight,
        LocalDateTime timeCreated) {
}
