package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.controllers.user.dto.ResetPasswordRequest;
import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import com.minder.MoneyMinder.models.ResetPasswordTokenEntity;
import com.minder.MoneyMinder.models.Role;
import com.minder.MoneyMinder.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserEntity registerUserRequestToUserEntity(RegisterUserRequest registerUserRequest, String encodedPassword, Role role) {
        return new UserEntity(registerUserRequest.name(), encodedPassword, registerUserRequest.email(), role);
    }

    UserResponse userEntityToUserResponse(UserEntity userEntity);

    default ResetPasswordTokenEntity resetPasswordRequestToResetPasswordTokenEntity(ResetPasswordRequest resetPasswordRequest, String name, int tokenExpiration){
        return new ResetPasswordTokenEntity(UUID.randomUUID().toString(), resetPasswordRequest.email(), name, tokenExpiration);
    }
}
