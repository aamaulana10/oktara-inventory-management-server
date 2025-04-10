package com.aressa.oktaraserver.user.mapper;

import com.aressa.oktaraserver.user.dto.UserDTO;
import com.aressa.oktaraserver.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }
}
