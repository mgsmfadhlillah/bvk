package com.bvk.springjwt.services.impl;

import com.bvk.springjwt.models.AppUser;
import com.bvk.springjwt.repository.UserRepository;
import com.bvk.springjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepo;
    public Optional<AppUser> findByUsername(String username){ return userRepo.findByEmail(username); }
    public Optional<AppUser> getAppUsersByUsername(String  uname) { return userRepo.getAppUsersByEmail(uname); }
    public Optional<AppUser> findByUsernameAndEncryptedPassword(String uname, String pass) { return userRepo.findByEmailAndPassword(uname, pass); }
    public void saveAndFlush(AppUser user){
        userRepo.saveAndFlush(user);
    }
    public void save(AppUser user){
        userRepo.save(user);
    }
}
