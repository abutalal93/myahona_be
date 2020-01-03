package com.decoders.leaves.service;


import com.decoders.leaves.entities.SystemUser;

public interface SystemUserService {
    public SystemUser login(SystemUser systemUser);
    public SystemUser findByUsername(String username);
}
