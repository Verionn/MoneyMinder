package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.user.dto.*;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    LoginResponse register(RegisterUserRequest registerUserRequest);

    Optional<LoginResponse> login(LoginRequest loginRequest);

    Either<UserResponse, Integer> getUserByEmail();

    boolean checkIfEmailExists(String email);

    Optional<LoginResponse> changePassword(ChangePasswordRequest changePasswordRequest, UserResponse user);
}
