package com.minder.MoneyMinder.controllers.user.dto;

public record AuthenticationRequest(
        String email,
        String password) {
}
