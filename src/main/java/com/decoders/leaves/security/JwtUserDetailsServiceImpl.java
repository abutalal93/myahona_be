package com.decoders.leaves.security;

import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;

    private String userType;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        System.out.println("userType: " + userType);

        switch (this.userType) {
            case "SYS":
                return this.findSystemUser(username);
            case "MOB":
                return this.findMobileUser(username);
            default:
                throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }

    private UserDetails findSystemUser(String username){

        SystemUser systemUser = systemUserService.findByUsername(username);

        if (systemUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(systemUser.getPassword())//
                .authorities(systemUser.getStatus().getCode())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

    private UserDetails findMobileUser(String username){

        SystemUser systemUser = systemUserService.findByUsername(username);

        if (systemUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(systemUser.getPassword())//
                .authorities(systemUser.getStatus().getCode())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
