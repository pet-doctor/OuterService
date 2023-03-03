package com.petdoctor.outer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.RedisService;
import com.petdoctor.outer.tool.JsonProcessor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    public static final String HASH_KEY = "Users";
    private final RedisTemplate<String, AppUser> redisTemplate;

    @Override
    public void set(AppUser user) {

        redisTemplate.opsForHash().put(HASH_KEY, user.getUsername(), user);

    }

    @Override
    public AppUser get(String username) {

        AppUser user = (AppUser) redisTemplate.opsForHash().get(HASH_KEY, username);

        if (user == null) {
            throw new RuntimeException();
        }

        return user;
    }
}
