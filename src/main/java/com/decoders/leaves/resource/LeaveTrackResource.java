package com.decoders.leaves.resource;


import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveTrackResource {

    private Long id;
    private String text;
    private LocalDateTime createDate;
    private Long leaveId;
    private String leaveNumber;
    private Long statusId;
    private String statusCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveNumber() {
        return leaveNumber;
    }

    public void setLeaveNumber(String leaveNumber) {
        this.leaveNumber = leaveNumber;
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

    public static LeaveTrackResource toResource(LeaveTrack leaveTrack)
    {
        LeaveTrackResource leaveTrackResource = new LeaveTrackResource();

        leaveTrackResource.setId(leaveTrack.getId());
        leaveTrackResource.setText(leaveTrack.getText());
        leaveTrackResource.setCreateDate(leaveTrack.getCreateDate());

        if(leaveTrack.getStatus() != null){
            leaveTrackResource.setStatusId(leaveTrack.getStatus().getId());
            leaveTrackResource.setStatusCode(leaveTrack.getStatus().getCode());
        }

        if(leaveTrack.getLeave() != null){
            leaveTrackResource.setLeaveId(leaveTrack.getLeave().getId());
            leaveTrackResource.setLeaveNumber(leaveTrack.getLeave().getNumber());
        }

        return leaveTrackResource;
    }

    public static List<LeaveTrackResource> toResource(List<LeaveTrack> leaveTrackList){
        List<LeaveTrackResource> leaveTrackResourceList = new ArrayList<>();
        leaveTrackList.forEach(leaveTrack -> {
            LeaveTrackResource leaveTrackResource = toResource(leaveTrack);
            leaveTrackResourceList.add(leaveTrackResource);
        });
        return leaveTrackResourceList;
    }

    public LeaveTrack toLeaveTrack(){
        LeaveTrack leaveTrack = new LeaveTrack();
        leaveTrack.setId(this.id);
        leaveTrack.setText(this.text);
        leaveTrack.setCreateDate(this.createDate);

        if(this.statusId != null){
            Status status = new Status();
            status.setId(this.statusId);
            status.setCode(this.statusCode);
            leaveTrack.setStatus(status);
        }

        if(this.leaveId != null){
            Leave leave = new Leave();
            leave.setId(this.leaveId);
            leave.setNumber(this.leaveNumber);
            leaveTrack.setLeave(leave);
        }

        return leaveTrack;
    }
}
