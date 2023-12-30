package com.minder.MoneyMinder.controllers.user.dto;

public record UserResponse(
        Long userId,
        String email,
        String name,
        String role) {
}
