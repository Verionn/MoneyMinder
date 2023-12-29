package com.minder.MoneyMinder.controllers.user.dto;

public record UserResponse(
        long id,
        String email,
        String name,
        String role) {
}
