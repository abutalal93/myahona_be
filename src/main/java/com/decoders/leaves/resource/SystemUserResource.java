package com.decoders.leaves.resource;


import com.decoders.leaves.entities.Status;
import com.decoders.leaves.entities.SystemUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SystemUserResource {

    private Long id;
    private String username;
    private String password;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
    private Long statusId;
    private String statusCode;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static SystemUserResource toResource(SystemUser systemUser)
    {
        SystemUserResource systemUserResource = new SystemUserResource();

        systemUserResource.setId(systemUser.getId());
        systemUserResource.setUsername(systemUser.getUsername());
        systemUserResource.setAddress(systemUser.getAddress());
        systemUserResource.setEmail(systemUser.getEmail());
        systemUserResource.setFirstName(systemUser.getFirstName());
        systemUserResource.setLastName(systemUser.getLastName());
        systemUserResource.setMobile(systemUser.getMobile());
        systemUserResource.setCreationDate(systemUser.getCreationDate());
        systemUserResource.setExpiryDate(systemUser.getExpiryDate());


        if(systemUser.getStatus() != null) {
            systemUserResource.setStatusId(systemUser.getStatus().getId());
            systemUserResource.setStatusCode(systemUser.getStatus().getCode());
        }

        return systemUserResource;
    }

    public static List<SystemUserResource> toResource(List<SystemUser> systemUserList){
        List<SystemUserResource> systemUserResourceList = new ArrayList<>();
        systemUserList.forEach(systemUser -> {
            SystemUserResource systemUserResource = toResource(systemUser);
            systemUserResourceList.add(systemUserResource);
        });
        return systemUserResourceList;
    }

    public SystemUser toSystemUser(){
        SystemUser systemUser = new SystemUser();
        systemUser.setId(this.id);
        systemUser.setUsername(this.username);
        systemUser.setPassword(this.password);
        systemUser.setFirstName(this.firstName);
        systemUser.setLastName(this.lastName);
        systemUser.setCreationDate(this.creationDate);
        systemUser.setExpiryDate(this.expiryDate);
        systemUser.setMobile(this.mobile);
        systemUser.setAddress(this.address);

        if(this.statusId != null){
            Status status = new Status();
            status.setId(this.statusId);
            status.setCode(this.statusCode);
            systemUser.setStatus(status);
        }

        return systemUser;
    }
}
