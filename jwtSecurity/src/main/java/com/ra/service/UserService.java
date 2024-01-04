package com.ra.service;

import com.ra.dto.rp.UserResponseDto;
import com.ra.dto.rq.UserRequestLogin;
import com.ra.dto.rq.UserRequestRegister;
import com.ra.model.User;

import java.util.List;

public interface UserService {
    User register(UserRequestRegister user);
    UserResponseDto login(UserRequestLogin userRequestDto);
    List<User> getAll();

}
