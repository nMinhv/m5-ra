package com.ra.dto.rq;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor@Getter@Setter@Builder
public class UserRequestRegister {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
