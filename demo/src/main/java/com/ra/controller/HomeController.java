package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("list",list);
        return "home";
    }

}
