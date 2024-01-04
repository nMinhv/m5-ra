package com.ra.security.config;

import com.ra.security.jwt.AccessDenied;
import com.ra.security.jwt.JwtEntryPoint;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.jwt.JwtTokenFilter;
import com.ra.security.user_principal.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailService userDetailService;
    private JwtProvider jwtProvider;
    private JwtEntryPoint jwtEntryPoint;
    private JwtTokenFilter jwtTokenFilter;
    private AccessDenied accessDenied;

    @Autowired
    public SecurityConfig(JwtProvider provider
            , CustomUserDetailService customUserDetailService
            , JwtTokenFilter tokenFilter
            , AccessDenied accessDenied) {
        this.jwtProvider = provider;
        this.userDetailService = customUserDetailService;
        this.accessDenied = accessDenied;
        this.jwtTokenFilter = tokenFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.cors(auth -> auth.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*"));
                    config.setAllowedMethods(List.of("*"));
                    return config;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/admin", "/admin/**").authenticated()
                                .requestMatchers("/auth/**","/**").permitAll())
                .exceptionHandling((auth) -> auth
                        .authenticationEntryPoint(jwtEntryPoint)
                        .accessDeniedHandler(accessDenied))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

}
