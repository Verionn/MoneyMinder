package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.user.dto.RegisterUserRequest;
import com.minder.MoneyMinder.controllers.user.dto.UserModel;
import com.minder.MoneyMinder.controllers.user.dto.UserResponse;
import com.minder.MoneyMinder.models.Role;
import com.minder.MoneyMinder.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserEntity registerUserRequestToUserEntity(RegisterUserRequest registerUserRequest, String encodedPassword, Role role) {
        return new UserEntity(registerUserRequest.name(), encodedPassword, registerUserRequest.email(), role);
    }

    UserResponse userEntityToUserResponse(UserEntity userEntity);
}
