package com.decoders.leaves.config;


import com.decoders.leaves.entities.*;
import com.decoders.leaves.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseSeeder {

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private SystemUserRepo systemUserRepo;

    @Autowired
    private SystemUserRoleRepo systemUserRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;


    public void seed() {
        seedStatusTable();
        seedLeaveType();
        seedRoleTable();
        seedSystemUserTable();
    }

    private void seedStatusTable() {

        Status activeStatus = new Status();
        activeStatus.setCode("ALL_ACTIVE");
        activeStatus.setDescription("Active");
        statusRepo.save(activeStatus);

        Status inActiveStatus = new Status();
        inActiveStatus.setCode("ALL_INACTIVE");
        inActiveStatus.setDescription("Inactive");
        statusRepo.save(inActiveStatus);

        Status deletedStatus = new Status();
        deletedStatus.setCode("ALL_DELETED");
        inActiveStatus.setDescription("Deleted");
        statusRepo.save(deletedStatus);

        Status hrReceivedStatus = new Status();
        hrReceivedStatus.setCode("LEAVE_HR_RECEIVED");
        hrReceivedStatus.setDescription("HR Received");
        statusRepo.save(hrReceivedStatus);

        Status drReviewStatus = new Status();
        drReviewStatus.setCode("LEAVE_DR_REVIEW");
        drReviewStatus.setDescription("Doctor Review");
        statusRepo.save(drReviewStatus);

        Status drAcceptStatus = new Status();
        drAcceptStatus.setCode("LEAVE_DR_ACCEPT");
        drAcceptStatus.setDescription("Doctor Accept");
        statusRepo.save(drAcceptStatus);

        Status drRejectStatus = new Status();
        drRejectStatus.setCode("LEAVE_DR_REJECT");
        drRejectStatus.setDescription("Doctor Reject");
        statusRepo.save(drRejectStatus);

        Status hrAcceptStatus = new Status();
        hrAcceptStatus.setCode("LEAVE_HR_ACCEPT");
        hrAcceptStatus.setDescription("HR Accept");
        statusRepo.save(hrAcceptStatus);

        Status hrRejectOrderStatus = new Status();
        hrRejectOrderStatus.setCode("LEAVE_HR_REJECT");
        hrRejectOrderStatus.setDescription("HR Reject");
        statusRepo.save(hrRejectOrderStatus);

        Status completeStatus = new Status();
        completeStatus.setCode("LEAVE_COMPLETE");
        completeStatus.setDescription("Complete");
        statusRepo.save(completeStatus);
    }

    private void seedLeaveType() {

        LeaveType sickLeaveType = new LeaveType();
        sickLeaveType.setCode("SICK");
        sickLeaveType.setDescription("Sick Leave");
        leaveTypeRepo.save(sickLeaveType);

        LeaveType personalLeaveType = new LeaveType();
        personalLeaveType.setCode("PERSONAL");
        personalLeaveType.setDescription("Personal Leave");
        leaveTypeRepo.save(personalLeaveType);
    }

    private void seedRoleTable() {

        Role superAdminRole = new Role();
        superAdminRole.setCode("SUPER_ADMIN");
        superAdminRole.setDescription("Super Admin");
        roleRepo.save(superAdminRole);

    }

    private void seedSystemUserTable() {

        Status activeStatus = statusRepo.findStatusByCode("ALL_ACTIVE");

        SystemUser systemUserSuperAdmin = new SystemUser();
        systemUserSuperAdmin.setUsername("mohammad.87");
        systemUserSuperAdmin.setAddress("-");
        systemUserSuperAdmin.setCreationDate(LocalDateTime.now());
        systemUserSuperAdmin.setExpiryDate(LocalDateTime.now().plusYears(1));
        systemUserSuperAdmin.setEmail("mohammad@yahoo.com");
        systemUserSuperAdmin.setFirstName("mohammad");
        systemUserSuperAdmin.setLastName("dabbas");
        systemUserSuperAdmin.setMobile("+962791111111");
        systemUserSuperAdmin.setPassword(passwordEncoder.encode("1234"));
        systemUserSuperAdmin.setStatus(activeStatus);
        systemUserRepo.save(systemUserSuperAdmin);

        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setRole(roleRepo.findByCode("SUPER_ADMIN"));
        systemUserRole.setSystemUser(systemUserSuperAdmin);
        systemUserRoleRepo.save(systemUserRole);
    }
}
