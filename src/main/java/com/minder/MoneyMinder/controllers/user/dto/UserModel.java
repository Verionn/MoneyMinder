package com.minder.MoneyMinder.controllers.user.dto;

public record UserModel(
        String username,
        String password,
        String email) {
}
