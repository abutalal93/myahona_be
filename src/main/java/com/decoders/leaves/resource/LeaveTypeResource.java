package com.decoders.leaves.resource;


import com.decoders.leaves.entities.LeaveType;

import java.util.ArrayList;
import java.util.List;

public class LeaveTypeResource {

    private Long id;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static LeaveTypeResource toResource(LeaveType leaveType)
    {
        LeaveTypeResource leaveTypeResource = new LeaveTypeResource();

        leaveTypeResource.setId(leaveType.getId());
        leaveTypeResource.setCode(leaveType.getCode());
        leaveTypeResource.setDescription(leaveType.getDescription());

        return leaveTypeResource;
    }

    public static List<LeaveTypeResource> toResource(List<LeaveType> leaveTypeList){
        List<LeaveTypeResource> leaveTypeResourceList = new ArrayList<>();
        leaveTypeList.forEach(leaveType -> {
            LeaveTypeResource leaveTypeResource = toResource(leaveType);
            leaveTypeResourceList.add(leaveTypeResource);
        });
        return leaveTypeResourceList;
    }

    public LeaveType toLeaveType(){
        LeaveType leaveType = new LeaveType();
        leaveType.setId(this.id);
        leaveType.setCode(this.code);
        leaveType.setDescription(this.description);
        
        return leaveType;
    }
}
