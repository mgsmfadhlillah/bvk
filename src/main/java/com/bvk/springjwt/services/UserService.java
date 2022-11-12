package com.bvk.springjwt.services;

import com.bvk.springjwt.models.AppUser;

import java.util.Optional;

public interface UserService {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> getAppUsersByUsername(String uname);
    Optional<AppUser> findByUsernameAndEncryptedPassword(String uname, String pass);
    void saveAndFlush(AppUser user);
    void save(AppUser user);
}
