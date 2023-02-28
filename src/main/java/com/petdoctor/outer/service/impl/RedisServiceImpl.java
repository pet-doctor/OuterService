package com.petdoctor.outer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.RedisService;
import com.petdoctor.outer.tool.JsonProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, JsonNode> redisTemplate;

    @Override
    public void set(AppUser user) {

        JsonNode node = JsonProcessor.parseToJsonNode(user);

        if (node == null) {
            throw new RuntimeException("aboba");
        }

        String username = user.getUsername();
        JsonNode changedNode = JsonProcessor.removeFieldFromJson(username, node);

        redisTemplate.opsForValue().set(username, changedNode);
    }

    @Override
    public Optional<AppUser> get(String username) {

        JsonNode node = redisTemplate.opsForValue().get(username);

        if (node == null) {
            throw new RuntimeException();
        }

        return Optional.of(
                JsonProcessor.parseFromJsonNode(username, node));
    }
}
