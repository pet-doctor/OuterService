package com.petdoctor.outer.service;

import com.petdoctor.outer.model.AppUser;

import java.util.Optional;

public interface RedisService {

    void set(AppUser user);
    Optional<AppUser> get(String username);
}
