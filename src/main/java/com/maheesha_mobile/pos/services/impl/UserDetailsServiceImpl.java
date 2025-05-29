package com.maheesha_mobile.pos.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final String USERNAME = "Nipun";
    private final String PASSWORD = "1234";
    private final String ROLE = "ADMIN";


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!USERNAME.equals(username)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + ROLE));

        return new User(
                USERNAME,
                PASSWORD,
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
