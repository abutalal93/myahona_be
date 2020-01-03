package com.decoders.leaves.service.impl;

import com.decoders.leaves.entities.LeaveType;
import com.decoders.leaves.repository.LeaveTrackRepo;
import com.decoders.leaves.repository.LeaveTypeRepo;
import com.decoders.leaves.service.LeaveTrackService;
import com.decoders.leaves.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;
}
