package com.example.demo.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.database.entyties.Role;
import com.example.demo.database.entyties.User;
import com.example.demo.database.repositories.RoleRepository;
import com.example.demo.database.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
class UserAdmindConfig implements CommandLineRunner {
    
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void run(String... args){

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        Optional<User> user = userRepository.findByusername("admin");

        user.ifPresentOrElse(
          existingUser -> {
            System.out.println("User admin already exists");
        },
          () -> {
             userRepository.save(new User().builder().username("admin")
             .password(passwordEncoder.encode("123"))
             .roles(Set.of(roleAdmin))
             .build());
          }
        );
    }

}