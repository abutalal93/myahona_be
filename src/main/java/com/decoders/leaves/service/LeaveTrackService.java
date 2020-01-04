package com.decoders.leaves.service;


import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.SystemUser;

public interface LeaveTrackService {
    public LeaveTrack save(LeaveTrack leaveTrack , SystemUser systemUser);

}
