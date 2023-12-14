package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) throws UserException{
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException("aaaaaaaaa");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
