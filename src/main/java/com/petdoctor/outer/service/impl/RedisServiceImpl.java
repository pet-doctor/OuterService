package com.petdoctor.outer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.repository.UserRepository;
import com.petdoctor.outer.service.RedisService;
import com.petdoctor.outer.tool.JsonProcessor;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableRedisDocumentRepositories
public class RedisServiceImpl implements RedisService {

    private final UserRepository userRepository;

    @Override
    public void set(AppUser user) {

        JsonNode node = JsonProcessor.parseToJsonNode(user);

        if (node == null) {
            throw new RuntimeException("aboba");
        }

        String username = user.getUsername();
        JsonNode changedNode = JsonProcessor.removeFieldFromJson(username, node);

        userRepository.save(user);
    }

    @Override
    public AppUser get(String username) {

        AppUser appUser = userRepository.loadByUsername(username)
                .orElseThrow(RuntimeException::new);

        return appUser;
    }
}
