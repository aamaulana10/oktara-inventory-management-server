package com.aressa.oktaraserver.user.service;

import com.aressa.oktaraserver.user.dto.UserDTO;
import com.aressa.oktaraserver.user.mapper.UserMapper;
import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAllWithRole();
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        return UserMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
