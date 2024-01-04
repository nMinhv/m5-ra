package com.ra.service.Impl;

import com.ra.model.dto.request.AccountRequest;
import com.ra.model.dto.request.SignInRequest;
import com.ra.model.dto.request.SignUpRequest;
import com.ra.model.dto.response.AccountResponse;
import com.ra.model.dto.response.SignInResponse;
import com.ra.model.entity.M5Role;
import com.ra.model.entity.M5User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.principal.M5UserPrincipal;
import com.ra.service.UserService;
import com.ra.util.exception.BadRequestException;
import com.ra.util.exception.NotFoundException;
import com.ra.util.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           AuthenticationProvider authenticationProvider,
                           JwtProvider jwtProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationProvider = authenticationProvider;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(SignUpRequest signUpRequest) throws NotFoundException, BadRequestException {
        Set<M5Role> roles = new HashSet<>();
//        signUpRequest.getRoles().forEach(item ->
//                roles.add(roleRepository.getByRoleName(item).orElse(null)));
//        if (roles.contains(null)) {
//            throw new NotFoundException("Role not found");
//        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new BadRequestException("Email already exist");
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new BadRequestException("Username already exist");
        }if (userRepository.existsByUsername(signUpRequest.getPhone())){
            throw new BadRequestException("Phone already exist");
        }
        roles.add(roleRepository.getByRoleName("USER").orElse(null));
        Date date = new Date(new java.util.Date().getTime());
        M5User newUser = M5User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .fullName(signUpRequest.getFullName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phone(signUpRequest.getPhone())
                .status(true)
                .createAt(date)
                .roles(roles)
                .build();
        userRepository.save(newUser);
        return "register successful";
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) throws NotFoundException, UnAuthorizedException {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getUsername(), signInRequest.getPassword()
                    ));
        } catch (AuthenticationException exception) {
            throw new NotFoundException("Username or password invalid");
        }
        M5UserPrincipal userPrincipal = (M5UserPrincipal) authentication.getPrincipal();
        if (!userPrincipal.getUser().getStatus()) {
            throw new UnAuthorizedException("your account been looked");
        }
        String token = jwtProvider.generateAccessToken(userPrincipal);
        return SignInResponse.builder()
                .id(userPrincipal.getUser().getUserId())
                .token(token)
                .fullName(userPrincipal.getUser().getFullName())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Page<M5User> findAll(Pageable pageable, String sort, String col) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(col));
        if (sort.equals("des")) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(col).descending());
        }
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<M5User> findAllByUserName(Pageable pageable, String sort, String col, String key) {

        return userRepository.findAllByUsernameContaining(key, pageable);
    }

    @Override
    public M5User findUserById(Long uid) throws NotFoundException {
        M5User user = userRepository.findById(uid).orElse(null);
        if (user == null) {
            throw new NotFoundException("Not found user with ID " + uid);
        }
        return user;
    }

    @Override
    public M5User updateUserStatus(Long uid) throws NotFoundException, UnAuthorizedException {
        M5User user = findUserById(uid);
        Set<M5Role> roles = user.getRoles();
        for (M5Role role : roles) {
            if (role.getRoleName().equals("ADMIN")) {
                throw new UnAuthorizedException("Cant block admin");
            }
        }
        user.setStatus(!user.getStatus());
        return userRepository.save(user);
    }

    // user
    @Override
    public AccountResponse account(M5User user) {
        return dto(user);
    }

    @Override
    public AccountResponse changePassword(M5User user, AccountRequest accountRequest) {
        if (passwordEncoder.matches(accountRequest.getCurPass(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(accountRequest.getNewPassword()));
        }
        return dto(userRepository.save(user));
    }

    @Override
    public AccountResponse updateAccount(M5User user, AccountRequest account) {

        user.setAvatar(account.getAvatar());
        user.setEmail(account.getEmail());
        user.setPhone(account.getPhone());
        user.setFullName(account.getFullName());
        user.setUsername(account.getUsername());
        userRepository.save(user);
        return dto(user);
    }

    private AccountResponse dto(M5User user) {
        return AccountResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .build();
    }

    private boolean validUser() {

        return false;
    }
}
