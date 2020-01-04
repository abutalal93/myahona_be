package com.decoders.leaves.repository;

import com.decoders.leaves.entities.LeaveType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveTypeRepo extends CrudRepository<LeaveType,Long> {
    public LeaveType findLeaveTypeById(Long id);
    public LeaveType findLeaveTypeByCode(String code);
    public List<LeaveType> findAll();
}
