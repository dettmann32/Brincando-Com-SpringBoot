package com.example.demo.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.database.entyties.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
 Role findByName(String name);
    
} 
