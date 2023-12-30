package com.minder.MoneyMinder.controllers.user;

import com.minder.MoneyMinder.controllers.user.dto.LoginRequest;
import com.minder.MoneyMinder.controllers.user.dto.LoginResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import com.minder.MoneyMinder.services.UserService;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterUserRequest registerUserRequest){

        return ResponseEntity.ok(userService.register(registerUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(userService.login(loginRequest));
    }

    public Either<UserResponse, Integer> getUserByEmailFromSecurityContext() {
        return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
