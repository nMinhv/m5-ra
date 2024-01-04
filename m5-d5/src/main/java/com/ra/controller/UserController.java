package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/???/")
//    public ResponseEntity<List<User>> list(@RequestParam(required = false) String sort) {
//        List<User> list;
//        if (sort != null) {
//            list = userService.sortBy(sort);
//        } else {
//            list = userService.findAll();
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> something
            (@RequestParam(defaultValue = "0") int curPage,
             @RequestParam(defaultValue = "5") int limit,
             @RequestParam(required = false) String sort) {
        Pageable pageable = PageRequest.of(curPage, limit);
        Page<User> userPage = userService.userListPage(pageable,sort);
        Map<String, Object> data = new HashMap<>();
        data.put("user", userPage.getContent());
        data.put("showing", userPage.getSize());
        data.put("total", userPage.getTotalElements());
        data.put("totalPage", userPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> search(
            @PageableDefault(size = 3, page = 0) Pageable pageable,
            @RequestParam String keyword) {
        Page<User> userPage = userService.getAllUserByName(keyword, pageable);

        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) throws UserException {
        User userNew;
        try {
            userNew = userService.create(user);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userNew, HttpStatus.CREATED);
    }

}
