package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveRepo extends CrudRepository<Leave,Long> , JpaSpecificationExecutor {

    public Leave save(Leave leave);
    public Leave findLeaveById(Long id);

    @Query("select lev from Leave lev where lev.status.id <> 8 and lev.status.id <> 9")
    public List<Leave> findNonCompleatedLeave();
}
