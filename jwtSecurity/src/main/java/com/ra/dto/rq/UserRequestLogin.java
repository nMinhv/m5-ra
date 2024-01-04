package com.ra.dto.rq;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestLogin {
    private String username;
    private String password;
}
