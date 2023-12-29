package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.user.dto.AuthenticationRequest;
import com.minder.MoneyMinder.controllers.user.dto.AuthenticationResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthenticationResponse register(RegisterUserRequest registerUserRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
