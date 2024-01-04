package com.ra.security;

import com.ra.model.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> grantedAuthorities = user.getRoles()
                .stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList();
        return new CustomUserDetail(user, grantedAuthorities);
    }
}
