package com.ra.config;

import com.ra.security.UserDetailServices;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailServices userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        return security.authenticationProvider(authenticationProvider()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/admin", "/admin/**").authenticated()
                                .requestMatchers("/**").permitAll()
                ).formLogin(
                        login->login.loginPage("/login").
                                loginProcessingUrl("/login").
                                usernameParameter("username").
                                passwordParameter("password").
                                defaultSuccessUrl("/admin").
                                failureUrl("/login?err"))
                .exceptionHandling(exceptionHandling->exceptionHandling.accessDeniedPage("/forbidden"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
