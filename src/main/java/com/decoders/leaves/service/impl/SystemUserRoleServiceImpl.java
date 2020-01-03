package com.decoders.leaves.service.impl;


import com.decoders.leaves.repository.SystemUserRoleRepo;
import com.decoders.leaves.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    @Autowired
    private SystemUserRoleRepo systemUserRoleRepo;
}
