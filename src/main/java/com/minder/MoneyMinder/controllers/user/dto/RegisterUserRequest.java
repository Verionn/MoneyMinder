package com.minder.MoneyMinder.controllers.user.dto;

public record RegisterUserRequest(
        String name,
        String password,
        String email) {
}
