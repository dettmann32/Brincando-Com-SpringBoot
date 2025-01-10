package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.controllers.dtos.CreateUserDto;
import com.example.demo.database.entyties.Role;
import com.example.demo.database.entyties.User;
import com.example.demo.database.repositories.RoleRepository;
import com.example.demo.database.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class userController {
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;
    
    @Transactional
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto user) {
        Role basic = roleRepository.findByName(Role.Values.BASIC.name());
        Optional<User> userDB = userRepository.findByusername(user.username());

        if (userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User newUser = new User().builder()
        .username(user.username())
        .password(passwordEncoder.encode(user.password()))
        .roles(Set.of(basic)).build();

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
