package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.util.exception.UserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User create(User user) throws UserException;
    List<User> findAll();
    User findUser(Integer uid);
    List<User> sortBy(String ascOrDes);

    Page<User> userListPage(Pageable pageable,String sort);
    Page<User> getAllUserByName(String k,Pageable pageable);
}
