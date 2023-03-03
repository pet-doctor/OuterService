package com.petdoctor.outer.repository;

import com.petdoctor.outer.model.AppUser;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface UserRepository extends RedisDocumentRepository<AppUser, String> {

    Optional<AppUser> loadByUsername(String username);
}
