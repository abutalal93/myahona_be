package com.decoders.leaves.repository;

import com.decoders.leaves.entities.LeaveType;
import org.springframework.data.repository.CrudRepository;

public interface LeaveTypeRepo extends CrudRepository<LeaveType,Long> {
    public LeaveType findLeaveTypeById(Long id);
    public LeaveType findLeaveTypeByCode(String code);
}
