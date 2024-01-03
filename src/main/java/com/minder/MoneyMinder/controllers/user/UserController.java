package com.minder.MoneyMinder.controllers.user;

import com.minder.MoneyMinder.controllers.user.dto.LoginRequest;
import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterUserRequest registerUserRequest) {

        if(checkIfRegisterUserRequestIsInvalid(registerUserRequest)){
            return ResponseEntity.badRequest().build();
        }

        if(checkIfEmailIsRegistered(registerUserRequest.email())){
            return ResponseEntity.status(CONFLICT).build();
        }

        return ResponseEntity.ok(userService.register(registerUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        if(checkIfLoginRequestIsInvalid(loginRequest)){
            return ResponseEntity.badRequest().build();
        }

        if(!checkIfEmailIsRegistered(loginRequest.email())){
            return ResponseEntity.status(UNAUTHORIZED).build();
        }

        return userService.login(loginRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    private boolean checkIfRegisterUserRequestIsInvalid(RegisterUserRequest registerUserRequest) {
        return registerUserRequest.email().isBlank() || registerUserRequest.password().isBlank() || registerUserRequest.name().isBlank();
    }

    private boolean checkIfLoginRequestIsInvalid(LoginRequest loginRequest) {
        return loginRequest.email().isBlank() || loginRequest.password().isBlank();
    }

    private boolean checkIfEmailIsRegistered(String email) {
        return userService.checkIfEmailExists(email);
    }
}
