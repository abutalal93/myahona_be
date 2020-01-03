package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepo extends CrudRepository<Leave,Long> , JpaSpecificationExecutor {

    public Leave save(Leave leave);
    public Leave findLeaveById(Long id);
}
