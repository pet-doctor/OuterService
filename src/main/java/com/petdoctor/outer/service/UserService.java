package com.petdoctor.outer.service;

import com.petdoctor.outer.model.AppUser;

public interface UserService {
    AppUser registerUser(AppUser appUser);
    String login(AppUser appUser);
}
