package com.maheesha_mobile.pos.services;

import com.maheesha_mobile.pos.dto.UserDto;
import com.maheesha_mobile.pos.model.UserEntity;
import com.maheesha_mobile.pos.response.user.CreateUserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(UserDto userDto);
    List<UserEntity> getAllUserDetails();
}
