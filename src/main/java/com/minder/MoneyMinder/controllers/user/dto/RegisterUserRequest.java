package com.minder.MoneyMinder.controllers.user.dto;

public record RegisterUserRequest(
        String username,
        String password,
        String email) {
}
