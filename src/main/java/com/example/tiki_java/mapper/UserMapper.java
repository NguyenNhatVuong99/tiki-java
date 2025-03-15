package com.example.tiki_java.mapper;

import com.example.tiki_java.dto.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.example.tiki_java.entity.User;
import com.example.tiki_java.dto.response.UserResponse;
import com.example.tiki_java.dto.request.UserCreateRequest;
import com.example.tiki_java.dto.request.UserUpdateRequest;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    UserCreateRequest toUserCreateRequest(RegisterRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
