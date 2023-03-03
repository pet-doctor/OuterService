package com.petdoctor.outer.service.impl;

import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.RedisService;
import com.petdoctor.outer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerUser(AppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        redisService.set(user);
        return redisService.get(user.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        return redisService.get(username);
    }

    //    @Transactional
//    public UserView update(ObjectId id, UpdateUserRequest request) {
//        var user = userRepo.getById(id);
//        userEditMapper.update(request, user);
//
//        user = userRepo.save(user);
//
//        return userViewMapper.toUserView(user);
//    }

//    @Transactional
//    public UserView delete(ObjectId id) {
//        var user = userRepo.getById(id);
//
//        user.setUsername(
//                user.getUsername().replace("@", String.format("_%s@", user.getId().toString())));
//        user.setEnabled(false);
//        user = userRepo.save(user);
//
//        return userViewMapper.toUserView(user);
//    }
}
