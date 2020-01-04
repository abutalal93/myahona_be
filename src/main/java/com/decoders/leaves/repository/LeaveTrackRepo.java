package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveTrackRepo extends CrudRepository<LeaveTrack,Long> {

    public LeaveTrack findTopByLeaveOrderByIdDesc(Leave leave);

    public void deleteAllByLeave(Leave leave);
}
