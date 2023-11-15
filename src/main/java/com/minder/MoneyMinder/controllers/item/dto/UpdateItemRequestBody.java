package com.minder.MoneyMinder.controllers.item.dto;

public record UpdateItemRequestBody(
        Long listId,
        String name,
        Double price,
        Integer amount,
        Long categoryId,
        Long weight) {
}
