package com.ra.dto.rp;

import com.ra.model.User;
import lombok.*;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private final String type = "Bearer ";
    private User user;
    private String token;
    private Set<String> roles;

}
