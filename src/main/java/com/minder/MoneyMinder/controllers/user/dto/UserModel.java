package com.minder.MoneyMinder.controllers.user.dto;

public record UserModel(
        String name,
        String password,
        String email) {
}
