package com.decoders.leaves.service.impl;

import com.decoders.leaves.repository.LeaveTrackRepo;
import com.decoders.leaves.service.LeaveTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LeaveTrackServiceImpl implements LeaveTrackService {

    @Autowired
    private LeaveTrackRepo leaveTrackRepo;
}
