package com.minder.MoneyMinder.services.implementations;


import com.minder.MoneyMinder.controllers.user.dto.*;
import com.minder.MoneyMinder.models.ResetPasswordTokenEntity;
import com.minder.MoneyMinder.models.Role;
import com.minder.MoneyMinder.models.UserEntity;
import com.minder.MoneyMinder.models.VerifyEmailTokenEntity;
import com.minder.MoneyMinder.repositories.ResetPasswordTokenRepository;
import com.minder.MoneyMinder.repositories.UserRepository;
import com.minder.MoneyMinder.repositories.VerifyEmailTokenRepository;
import com.minder.MoneyMinder.services.UserService;
import com.minder.MoneyMinder.services.mappers.UserMapper;
import io.vavr.control.Either;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.minder.MoneyMinder.controllers.user.UserController.VERIFY_ACCOUNT_TOKEN_EXPIRATION_TIME_IN_MINUTES;

@Service
public class UserServiceImpl implements UserService {


    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final VerifyEmailTokenRepository verifyEmailTokenRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(JavaMailSender mailSender, UserRepository userRepository, ResetPasswordTokenRepository resetPasswordTokenRepository, VerifyEmailTokenRepository verifyEmailTokenRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.verifyEmailTokenRepository = verifyEmailTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(HttpServletRequest request, RegisterUserRequest registerUserRequest) {
        var user = userMapper.registerUserRequestToUserEntity(
                registerUserRequest,
                passwordEncoder.encode(registerUserRequest.password()),
                Role.USER);
        user.setTime_created(LocalDateTime.now());
        userRepository.save(user);

        String serverUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String verificationToken = UUID.randomUUID().toString();
        VerifyEmailTokenEntity verifyEmailTokenEntity = new VerifyEmailTokenEntity(
                verificationToken,
                registerUserRequest.email(),
                registerUserRequest.name(),
                VERIFY_ACCOUNT_TOKEN_EXPIRATION_TIME_IN_MINUTES);

        try {
            verifyEmailTokenRepository.save(verifyEmailTokenEntity);
            sendVerificationEmail(serverUrl, verifyEmailTokenEntity);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
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

    public Optional<LoginResponse> changePassword(ChangePasswordRequest changePasswordRequest, UserResponse user) {
        userRepository.findById(user.userId())
                .map(userEntity -> updateUserEntityPassword(userEntity, changePasswordRequest.newPassword()))
                .map(userRepository::save);

        return login(new LoginRequest(user.email(), changePasswordRequest.newPassword()));
    }

    @Override
    public void resetPassword(ResetPasswordTokenEntity resetPasswordTokenEntity, HttpServletRequest request) {
        resetPasswordTokenRepository.save(resetPasswordTokenEntity);
        String serverUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        try {
            sendResetPasswordEmail(serverUrl, resetPasswordTokenEntity);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> confirmResetPassword(String token, ConfirmResetPasswordRequest confirmResetPasswordRequest) {
        String userEmail = resetPasswordTokenRepository.findEmailByToken(token);
        userRepository.findByEmail(userEmail)
                .map(userEntity -> updateUserEntityPassword(userEntity, confirmResetPasswordRequest.password()))
                .map(userRepository::save);

        resetPasswordTokenRepository.deleteByToken(token);
        return ResponseEntity.ok().build();
    }

    @Override
    public String getNameByEmail(String email) {
        return userRepository.findByEmail(email).map(UserEntity::getName).orElse(null);
    }

    @Override
    public Boolean checkIfVerifyEmailTokenExists(String token) {
        return verifyEmailTokenRepository.existsByToken(token);
    }

    @Override
    public Boolean checkIfResetPasswordTokenExists(String token) {
        return resetPasswordTokenRepository.existsByToken(token);
    }

    @Override
    public ResetPasswordTokenEntity getResetPasswordTokenEntityByToken(String token) {
        return resetPasswordTokenRepository.getTokenEntityByToken(token);
    }

    @Override
    public VerifyEmailTokenEntity getVerifyEmailTokenEntityByToken(String token) {
        return verifyEmailTokenRepository.getTokenEntityByToken(token);
    }

    @Override
    public void removeToken(String token) {
        resetPasswordTokenRepository.deleteByToken(token);
    }

    @Override
    public void verify(String token) {
        String email = verifyEmailTokenRepository.findEmailByToken(token);
        userRepository.findByEmail(email)
                .map(this::verifyUser)
                .map(userRepository::save);
    }

    @Override
    public boolean checkIfUserIsVerified(String email) {
        return userRepository.checkIfUserIsVerified(email);
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
    public boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }


    private UserEntity updateUserEntityPassword(UserEntity userEntity, String password) {
        userEntity.setPassword(passwordEncoder.encode(password));
        return userEntity;
    }

    private UserEntity verifyUser(UserEntity userEntity) {
        userEntity.setVerified(true);
        return userEntity;
    }

    private void sendResetPasswordEmail(String contextPath, ResetPasswordTokenEntity resetPasswordTokenEntity) throws MessagingException, UnsupportedEncodingException {
        String url = contextPath + "/user/confirm-reset-password?token=" + resetPasswordTokenEntity.getToken();
        String mailContent = "<p>Hi, <b>" + resetPasswordTokenEntity.getName() + "</b>,</p>" +
                "<p>Please click the link below to reset your password!</p>" +
                "<a href=\"" + url + "\">CLICK TO RESET PASSWORD</a>" +
                "<p>If you did not request a password reset, you can safely ignore this email.</p>" +
                "<p>Only a person with access to your email can reset your account password.</p>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("MoneyMinder", "MoneyMinder");
        messageHelper.setTo(resetPasswordTokenEntity.getEmail());
        messageHelper.setSubject("Reset Password");
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    private void sendVerificationEmail(String contextPath, VerifyEmailTokenEntity verifyEmailTokenEntity) throws MessagingException, UnsupportedEncodingException {
        String url = contextPath + "/user/verify-email?token=" + verifyEmailTokenEntity.getToken();
        String mailContent = "<p>Hi, <b>" + verifyEmailTokenEntity.getName() + "</b>,</p>" +
                "<p>Please click the link below to verify your account!</p>" +
                "<a href=\"" + url + "\">CLICK TO VERIFY ACCOUNT</a>" +
                "<p>If you did not create account on our website, you can safely ignore this email.</p>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("MoneyMinder", "MoneyMinder");
        messageHelper.setTo(verifyEmailTokenEntity.getEmail());
        messageHelper.setSubject("Email verification");
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
