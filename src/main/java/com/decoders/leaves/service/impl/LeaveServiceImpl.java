package com.decoders.leaves.service.impl;


import com.decoders.leaves.repository.LeaveRepo;
import com.decoders.leaves.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;
}