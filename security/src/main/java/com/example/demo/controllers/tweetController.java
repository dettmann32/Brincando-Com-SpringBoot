package com.example.demo.controllers;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.CreateTweetDto;
import com.example.demo.database.entyties.Tweet;
import com.example.demo.database.entyties.User;
import com.example.demo.database.repositories.TweetRepository;
import com.example.demo.database.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class tweetController {
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto tweet, JwtAuthenticationToken token) {
        Optional<User> user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        tweetRepository.save(new Tweet().builder()
        .content(tweet.content())
        .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets")
    public ResponseEntity<Page<Tweet>> getTweets(Pageable pageable){
        return ResponseEntity.ok(tweetRepository.findAll(pageable));
    }
}
