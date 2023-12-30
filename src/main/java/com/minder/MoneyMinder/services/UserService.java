package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.user.dto.LoginRequest;
import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    LoginResponse register(RegisterUserRequest registerUserRequest);

    Optional<LoginResponse> login(LoginRequest loginRequest);

    Either<UserResponse, Integer> getUserByEmail();
}
