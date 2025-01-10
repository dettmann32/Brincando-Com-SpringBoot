package com.example.demo.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.database.entyties.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    
}
