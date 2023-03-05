package com.petdoctor.outer.service.impl;

import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.RedisService;
import com.petdoctor.outer.service.UserService;
import com.petdoctor.outer.tool.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(AppUser appUser) {

        UserDetails foundUser = loadUserByUsername(appUser.getUsername());

        return JwtUtils.generateToken(foundUser);
    }

    @Override
    public AppUser registerUser(AppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        redisService.set(user);
        return redisService.get(user.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        AppUser user = redisService.get(username);
        if (user == null)
            throw new UsernameNotFoundException("Username not found in db");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new User(user.getUsername(), user.getPassword(), authorities);
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
