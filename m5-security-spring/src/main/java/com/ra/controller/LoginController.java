package com.ra.controller;

import com.ra.model.User;
import com.ra.model.UserRole;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user){
        UserRole role = roleRepository.findById(2L).orElseThrow(()-> new RuntimeException("role not found"));
        Set<UserRole> roles = new HashSet<>();
        roles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/login";
    }
}
