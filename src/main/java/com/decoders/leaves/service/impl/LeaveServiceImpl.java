package com.decoders.leaves.service.impl;


import com.decoders.leaves.Utils.Utils;
import com.decoders.leaves.entities.*;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.repository.LeaveRepo;
import com.decoders.leaves.repository.LeaveTrackRepo;
import com.decoders.leaves.repository.LeaveTypeRepo;
import com.decoders.leaves.repository.StatusRepo;
import com.decoders.leaves.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    @Autowired
    private LeaveTrackRepo leaveTrackRepo;

    @Override
    public Leave save(Leave leave, SystemUser systemUser) {

        LeaveType leaveType = leaveTypeRepo.findLeaveTypeById(leave.getLeaveType().getId());

        if(leaveType == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_type_not_found");
        }

        Status status = statusRepo.findStatusById(leave.getStatus().getId());

        if(status == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_type_not_found");
        }

        leave.setLeaveType(leaveType);
        leave.setStatus(status);

        leave.setCreateDate(LocalDateTime.now());
        leave.setNumber(Utils.generateLeaveNumber());

        leave = leaveRepo.save(leave);

        List<Status> statusList = statusRepo.findStausTrack(status.getId());

        for(Status statusTrack: statusList){
            LeaveTrack leaveTrack = new LeaveTrack();
            leaveTrack.setLeave(leave);
            leaveTrack.setStatus(statusTrack);
            leaveTrack.setSystemUser(systemUser);
            leaveTrack.setCreateDate(LocalDateTime.now());
            leaveTrackRepo.save(leaveTrack);
        }

        return leave;
    }

    @Override
    public Leave update(Leave leave, SystemUser systemUser) {
        LeaveType leaveType = leaveTypeRepo.findLeaveTypeById(leave.getLeaveType().getId());

        if(leaveType == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_type_not_found");
        }

        Status status = statusRepo.findStatusById(leave.getStatus().getId());

        if(status == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_type_not_found");
        }

        Leave currentLeave = leaveRepo.findLeaveById(leave.getId());

        if(currentLeave == null){
            throw new ResourceException(HttpStatus.NOT_FOUND, "leave_not_found");
        }

        currentLeave.setLeaveType(leaveType);
        currentLeave.setStatus(status);
        currentLeave.setStartDate(leave.getStartDate());
        currentLeave.setEndDate(leave.getEndDate());
        currentLeave.setNumberOfDays(leave.getNumberOfDays());
        currentLeave.setReceiveDate(leave.getReceiveDate());

        return currentLeave;

    }

    @Override
    public Page<Leave> findAll(Leave leave, Integer page, Integer size) {
        if (page == null) page = 0;
        if (size == null) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        Page<Leave> leavePage = leaveRepo.findAll(new Specification<Leave>() {

            @Override
            public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (leave.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), leave.getId()));
                }

                if (leave.getNumber() != null) {
                    predicates.add(cb.like(cb.lower(root.get("number")), "%" + leave.getNumber().toLowerCase() + "%"));
                }

                if (leave.getEmployeeNumber() != null) {
                    predicates.add(cb.like(cb.lower(root.get("employeeNumber")), "%" + leave.getEmployeeNumber().toLowerCase() + "%"));
                }

                predicates.add(cb.notEqual(root.get("status"), statusRepo.findStatusByCode("ALL_DELETED")));

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        }, pageable);

        return leavePage;
    }
}
