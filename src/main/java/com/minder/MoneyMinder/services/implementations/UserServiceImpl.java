package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.controllers.user.dto.AuthenticationRequest;
import com.minder.MoneyMinder.controllers.user.dto.AuthenticationResponse;
import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.models.Role;
import com.minder.MoneyMinder.repositories.UserRepository;
import com.minder.MoneyMinder.services.UserService;
import com.minder.MoneyMinder.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AuthenticationResponse register(RegisterUserRequest registerUserRequest) {
        var user = userMapper.registerUserRequestToUserEntity(
                registerUserRequest,
                passwordEncoder.encode(registerUserRequest.password()),
                Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.email())
                .orElseThrow(); //tego nie musi byc?
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
