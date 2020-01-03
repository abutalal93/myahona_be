package com.decoders.leaves.service.impl;


import com.decoders.leaves.repository.RoleRepo;
import com.decoders.leaves.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;
}
