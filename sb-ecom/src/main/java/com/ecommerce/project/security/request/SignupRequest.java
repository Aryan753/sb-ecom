package com.ecommerce.project.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class SignupRequest {
    @NotBlank
    @Size(min=4,max = 12)
    private String username;
    @NotBlank
    @Size(max = 25)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min=6,max=20)
    private String password;
}
