package com.ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignUpRequest {
    @NotBlank(message = "username can't be empty")
    private String username;
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "email invalid")
    private String email;
    private String fullName;
    @Pattern(regexp = "^(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "phone invalid")
    private String phone;
    private Set<String> roles;
}
