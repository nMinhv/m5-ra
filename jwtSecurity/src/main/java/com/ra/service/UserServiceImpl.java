package com.ra.service;

import com.ra.dto.rp.UserResponseDto;
import com.ra.dto.rq.UserRequestLogin;
import com.ra.dto.rq.UserRequestRegister;
import com.ra.model.User;
import com.ra.model.UserRole;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.user_principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User register(UserRequestRegister user) {
        Set<UserRole> roles = new HashSet<>();
        user.getRoles().forEach(item -> {
            roles.add(roleRepository.findByName(item)
                    .orElseThrow(() -> new RuntimeException(item + " not found")));
        });
        User newUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .status(true)
                .roles(roles)
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDto login(UserRequestLogin userRequestDto) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),userRequestDto.getPassword()));
        }catch (AuthenticationException authenticationException){
            throw new RuntimeException("Username or password invalid");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtProvider.generateAccessToken(userPrincipal);
        UserResponseDto responseDto = UserResponseDto.builder()
                .user(userPrincipal.getUser())
                .token(token)
                .roles(userPrincipal.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet()))
                .build();
        return responseDto;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
