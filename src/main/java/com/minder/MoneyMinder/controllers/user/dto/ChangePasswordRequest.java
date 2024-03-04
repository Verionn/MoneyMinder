package com.minder.MoneyMinder.controllers.user.dto;

public record ChangePasswordRequest(
        String oldPassword,
        String repeatOldPassword,
        String newPassword
) {
}
