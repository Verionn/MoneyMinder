package com.minder.MoneyMinder.services;


import com.minder.MoneyMinder.controllers.user.dto.*;
import com.minder.MoneyMinder.models.ResetPasswordTokenEntity;
import io.vavr.control.Either;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void register(HttpServletRequest request, RegisterUserRequest registerUserRequest);

    Optional<LoginResponse> login(LoginRequest loginRequest);

    Either<UserResponse, Integer> getUserByEmail();

    boolean checkIfEmailExists(String email);


    Optional<LoginResponse> changePassword(ChangePasswordRequest changePasswordRequest, UserResponse user);

    void resetPassword(ResetPasswordTokenEntity resetPasswordTokenEntity, HttpServletRequest request);

    ResponseEntity<HttpStatus> confirmResetPassword(String token, ConfirmResetPasswordRequest confirmResetPasswordRequest);

    String getNameByEmail(String email);

    Boolean checkIfVerifyEmailTokenExists(String token);

    Boolean checkIfResetPasswordTokenExists(String token);

    ResetPasswordTokenEntity getResetPasswordTokenEntityByToken(String token);

    void removeToken(String token);

    void verify(String token);

    boolean checkIfUserIsVerified(String email);
}
