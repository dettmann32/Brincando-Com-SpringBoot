package com.example.demo.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //habilita a segurança por anotação
public class SecurityConfig {
    @Value("${jwt.private.key}") //variavel definida no apllication.yml
    private RSAPrivateKey privateKey;

    @Value("${jwt.public.key}") //variavel definida no apllication.yml
    private RSAPublicKey publicKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http 
         //define a rota de login
        .authorizeHttpRequests(authz -> authz.requestMatchers(HttpMethod.POST, "/login").permitAll() //autorização para rotas
        .requestMatchers(HttpMethod.POST, "/user").permitAll()
        .requestMatchers(HttpMethod.GET, "/").permitAll()
        .anyRequest().authenticated()) //autorização para rotas
        .csrf(csrf -> csrf.disable()) //desabilita a proteção de csrf (apenas para ambiente de testes)
        .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))//configura o oauth2 com jwt
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//define o tipo de seção como stateless


        return http.build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);        
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
