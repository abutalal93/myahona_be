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


    public void seed() {
        seedStatusTable();
        seedRoleTable();
        seedSystemUserTable();
    }

    private void seedStatusTable() {

        Status activeStatus = new Status();
        activeStatus.setCode("ALL_ACTIVE");
        statusRepo.save(activeStatus);

        Status inActiveStatus = new Status();
        inActiveStatus.setCode("ALL_INACTIVE");
        statusRepo.save(inActiveStatus);

        Status deletedStatus = new Status();
        deletedStatus.setCode("ALL_DELETED");
        statusRepo.save(deletedStatus);

        Status hrReceivedStatus = new Status();
        hrReceivedStatus.setCode("LEAVE_HR_RECEIVED");
        statusRepo.save(hrReceivedStatus);

        Status drReviewStatus = new Status();
        drReviewStatus.setCode("LEAVE_DR_REVIEW");
        statusRepo.save(drReviewStatus);

        Status drAcceptStatus = new Status();
        drAcceptStatus.setCode("LEAVE_DR_ACCEPT");
        statusRepo.save(drAcceptStatus);

        Status drRejectStatus = new Status();
        drRejectStatus.setCode("LEAVE_DR_REJECT");
        statusRepo.save(drRejectStatus);

        Status hrAcceptStatus = new Status();
        hrAcceptStatus.setCode("LEAVE_HR_ACCEPT");
        statusRepo.save(hrAcceptStatus);

        Status hrRejectOrderStatus = new Status();
        hrRejectOrderStatus.setCode("LEAVE_HR_REJECT");
        statusRepo.save(hrRejectOrderStatus);

        Status completeStatus = new Status();
        completeStatus.setCode("LEAVE_COMPLETE");
        statusRepo.save(completeStatus);
    }


    private void seedRoleTable() {

        Role superAdminRole = new Role();
        superAdminRole.setCode("SUPER_ADMIN");
        superAdminRole.setDescription("Super Admin");
        roleRepo.save(superAdminRole);

    }


    private void seedSystemUserTable() {

        Status activeStatus = statusRepo.findStatusByCode("ACTIVE");

        SystemUser systemUserSuperAdmin = new SystemUser();
        systemUserSuperAdmin.setUsername("admin");
        systemUserSuperAdmin.setAddress("-");
        systemUserSuperAdmin.setCreationDate(LocalDateTime.now());
        systemUserSuperAdmin.setExpiryDate(LocalDateTime.now().plusYears(1));
        systemUserSuperAdmin.setEmail("ali@yahoo.com");
        systemUserSuperAdmin.setFirstName("ali");
        systemUserSuperAdmin.setLastName("mohammad");
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
