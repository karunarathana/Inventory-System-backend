package com.maheesha_mobile.pos.services.impl;

import com.maheesha_mobile.pos.model.UserEntity;
import com.maheesha_mobile.pos.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo userRepo;
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with username: " + username);
        UserEntity userEntity = foundUserDetails(username);
        if (!userEntity.getUserName().equals(username)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));

        return new User(
                userEntity.getUserName(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
    private UserEntity foundUserDetails(String userName){
        Optional<UserEntity> details = userRepo.findByUserName(userName);
        System.out.println(details.get().getUserId());
        return details.get();
    }
}
