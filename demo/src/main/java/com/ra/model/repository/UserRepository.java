package com.ra.model.repository;

import com.ra.model.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User saveOrUpdate(User user);

    void delete(Integer id);
}
