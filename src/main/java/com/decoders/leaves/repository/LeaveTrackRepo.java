package com.decoders.leaves.repository;

import com.decoders.leaves.entities.LeaveTrack;
import org.springframework.data.repository.CrudRepository;

public interface LeaveTrackRepo extends CrudRepository<LeaveTrack,Long> {
}
