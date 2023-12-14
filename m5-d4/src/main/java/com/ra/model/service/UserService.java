package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.util.exception.UserException;

import java.util.List;

public interface UserService {
    User create(User user) throws UserException;
    List<User> findAll();
}
