package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.controllers.user.dto.*;
import com.minder.MoneyMinder.models.Role;
import com.minder.MoneyMinder.models.UserEntity;
import com.minder.MoneyMinder.repositories.UserRepository;
import com.minder.MoneyMinder.services.UserService;
import com.minder.MoneyMinder.services.mappers.UserMapper;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse register(RegisterUserRequest registerUserRequest) {
        var user = userMapper.registerUserRequestToUserEntity(
                registerUserRequest,
                passwordEncoder.encode(registerUserRequest.password()),
                Role.USER);
        user.setTime_created(LocalDateTime.now());
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);
    }

    @Override
    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        return userRepository.findByEmail(loginRequest.email())
                .map(user -> {
                    var jwtToken = jwtService.generateToken(user);
                    return new LoginResponse(jwtToken);
                });
    }

    @Override
    public Either<UserResponse, Integer> getUserByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserResponse)
                .map(Either::<UserResponse, Integer>left)
                .orElseGet(() -> Either.right(404));
    }

    @Override
    public Optional<LoginResponse> changePassword(ChangePasswordRequest changePasswordRequest, UserResponse user) {
        userRepository.findById(user.userId())
                .map(userEntity -> updateUserEntity(userEntity, changePasswordRequest))
                .map(userRepository::save);

        return login(new LoginRequest(user.email(), changePasswordRequest.newPassword()));
    }

    private UserEntity updateUserEntity(UserEntity userEntity, ChangePasswordRequest changePasswordRequest) {
        userEntity.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        return userEntity;
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
