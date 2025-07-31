package com.example.springsecurity.service;

import com.example.springsecurity.entity.AppUser;
import com.example.springsecurity.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user"));

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getAppRole().getRoleName())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getEncrytedPassword(),
                user.isEnabled(),
                true, true, true,
                authorities
        );
    }
}

