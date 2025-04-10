package com.aressa.oktaraserver.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String userName;
    private String password;
}
