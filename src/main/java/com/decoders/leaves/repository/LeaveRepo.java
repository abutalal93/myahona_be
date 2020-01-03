package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Leave;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepo extends CrudRepository<Leave,Long> {
}
