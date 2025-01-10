package com.example.demo.controllers;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.LoginRequest;
import com.example.demo.controllers.dtos.LoginResponse;
import com.example.demo.database.entyties.Role;
import com.example.demo.database.entyties.User;
import com.example.demo.database.repositories.UserRepository;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class tokenController {
    JwtEncoder jwtEncoder;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginData){
        Optional<User> user = userRepository.findByusername(loginData.username());

        if(user.isEmpty() || !passwordEncoder.matches(loginData.password(), user.get().getPassword())){
            throw new BadCredentialsException("The passwrod or username is incorrect");
        }

        Instant now = Instant.now();
        long expiration = 300L;
        String scopes = user.get().getRoles()
        .stream().map(Role::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
        .issuer("https://example.com"/*mybackend*/) //se refere ao emissor do token
        .expiresAt(now.plusSeconds(expiration))
        .claim("scope", scopes)//se refere ao escopo do token, ADMIN ou BASIC
        .issuedAt(now)
        .subject(user.get().getUserId().toString())//se refere ao dono do token
        .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiration));
    }

    
    
}
