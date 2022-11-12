package com.bvk.springjwt.config.security.services;

import com.bvk.springjwt.repository.AppRoleRepository;
import com.bvk.springjwt.repository.UserRepository;
import com.bvk.springjwt.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  AppRoleRepository appRoleRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found : " + username));

    List<String> roleNames = appRoleRepo.getRoleNames(user.getUserId());
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    if (roleNames != null) {
      for (String role : roleNames) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);
      }
    }
    return UserDetailsImpl.build(user, authorities);
  }
}
