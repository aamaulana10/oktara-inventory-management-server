package com.aressa.oktaraserver.security;

import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder builder = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName());
        builder.password(user.getPassword());
        builder.roles(user.getRole().getName()); // ROLE_ADMIN, ROLE_USER, dll

        return builder.build();
    }
}

