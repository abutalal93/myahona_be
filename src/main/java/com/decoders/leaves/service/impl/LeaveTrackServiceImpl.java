package com.decoders.leaves.service.impl;

import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.Status;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.repository.LeaveRepo;
import com.decoders.leaves.repository.LeaveTrackRepo;
import com.decoders.leaves.repository.StatusRepo;
import com.decoders.leaves.service.LeaveTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LeaveTrackServiceImpl implements LeaveTrackService {

    @Autowired
    private LeaveTrackRepo leaveTrackRepo;

    @Autowired
    private LeaveRepo leaveRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public LeaveTrack save(LeaveTrack leaveTrack, SystemUser systemUser) {

        Leave leave = leaveRepo.findLeaveById(leaveTrack.getLeave().getId());

        if (leave == null) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_not_found");
        }

        Status status = statusRepo.findStatusById(leaveTrack.getStatus().getId());

        if (status == null) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_type_not_found");
        }

        LeaveTrack lastLeaveTrack = leaveTrackRepo.findTopByLeaveOrderByIdDesc(leave);


        leaveTrack.setLeave(leave);
        leaveTrack.setStatus(status);
        leaveTrack.setSystemUser(systemUser);
        leaveTrack.setCreateDate(LocalDateTime.now());

        leaveTrack = leaveTrackRepo.save(leaveTrack);


        return leaveTrack;
    }
}
