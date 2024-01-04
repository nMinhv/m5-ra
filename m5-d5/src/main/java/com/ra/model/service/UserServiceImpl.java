package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) throws UserException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException("aaaaaaaaa");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Integer uid) {
        Optional<User> optionalUser = userRepository.findById(uid);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> sortBy(String ascOrDes) {
        if (ascOrDes.equalsIgnoreCase("desc")) {
            return userRepository.findAll(Sort.by("fullName").descending());
        }
        return userRepository.findAll(Sort.by("fullName").ascending());
    }

    @Override
    public Page<User> userListPage(Pageable pageable, String sort) {
        if (sort != null) {
            if (sort.equals("desc")) {
                 pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("fullName").descending());
            } else {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("fullName").ascending());
            }
        }
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUserByName(String k, Pageable pageable) {

        return userRepository.getAllByFullNameContaining(k, pageable);
    }


}
