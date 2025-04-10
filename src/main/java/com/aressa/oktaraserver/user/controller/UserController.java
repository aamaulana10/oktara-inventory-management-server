package com.aressa.oktaraserver.user.controller;

import com.aressa.oktaraserver.user.dto.UserDTO;
import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("🔥 Creating user: " + user);
        return userService.createUser(user);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        System.out.println("GET /api/users called");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PutMapping("/{id}")
//    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        return userService.updateUser(id, userDTO);
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
