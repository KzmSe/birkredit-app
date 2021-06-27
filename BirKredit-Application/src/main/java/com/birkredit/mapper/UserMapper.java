package com.birkredit.mapper;

import com.birkredit.controller.user.dto.UserCreationRequest;
import com.birkredit.controller.user.dto.UserResponse;
import com.birkredit.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userCreationRequestToUser(UserCreationRequest request);

    UserResponse userToUserResponse(User user);
}