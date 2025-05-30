package com.maheesha_mobile.pos.services;

import com.maheesha_mobile.pos.dto.LoginDto;
import com.maheesha_mobile.pos.dto.UserDto;
import com.maheesha_mobile.pos.response.user.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(UserDto userDto);
    String validateUser(LoginDto loginDto);
    String forgotPassword(String userEmail,String newPassword);
}
