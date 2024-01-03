package com.minder.MoneyMinder.controllers.user.dto;

public record LoginRequest(
        String email,
        String password) {
}
