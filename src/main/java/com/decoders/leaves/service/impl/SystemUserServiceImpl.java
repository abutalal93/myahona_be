package com.decoders.leaves.service.impl;

import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.repository.SystemUserRepo;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepo systemUserRepo;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public SystemUser login(SystemUser systemUser) {

        SystemUser currentSystemUser = systemUserRepo.findByUsername(systemUser.getUsername());

        if(currentSystemUser == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "username_not_found");
        }

        boolean passwordMatch = passwordEncoder.matches(systemUser.getPassword(),currentSystemUser.getPassword());

        if(!passwordMatch){
            throw new ResourceException(HttpStatus.UNAUTHORIZED, "password_invalid");
        }

        return currentSystemUser;
    }

    @Override
    public SystemUser findByUsername(String username) {
        return systemUserRepo.findByUsername(username);
    }
}
