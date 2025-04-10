package com.aressa.oktaraserver.auth.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "full_name", nullable = false)
    private String fullName;
    private String email;
    private String password;

    @Column(name = "role_id", nullable = false)
    private Long role;
}
