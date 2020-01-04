package com.decoders.leaves.resource;

import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.LeaveType;
import com.decoders.leaves.entities.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveResource {

    private Long id;
    private String number;
    private String employeeNumber;
    private LocalDateTime createDate;
    private LocalDateTime receiveDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long numberOfDays;
    private Long statusId;
    private String statusCode;
    private Long leaveTypeId;
    private String leaveTypeCode;
    private List<LeaveTrackResource> leaveTrackResourceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
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

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeCode() {
        return leaveTypeCode;
    }

    public void setLeaveTypeCode(String leaveTypeCode) {
        this.leaveTypeCode = leaveTypeCode;
    }

    public List<LeaveTrackResource> getLeaveTrackResourceList() {
        return leaveTrackResourceList;
    }

    public void setLeaveTrackResourceList(List<LeaveTrackResource> leaveTrackResourceList) {
        this.leaveTrackResourceList = leaveTrackResourceList;
    }

    public static LeaveResource toResource(Leave leave)
    {
        LeaveResource leaveResource = new LeaveResource();

        leaveResource.setId(leave.getId());
        leaveResource.setNumber(leave.getNumber());
        leaveResource.setEmployeeNumber(leave.getEmployeeNumber());
        leaveResource.setCreateDate(leave.getCreateDate());
        leaveResource.setReceiveDate(leave.getReceiveDate());
        leaveResource.setStartDate(leave.getStartDate());
        leaveResource.setEndDate(leave.getEndDate());
        leaveResource.setNumberOfDays(leave.getNumberOfDays());

        if(leave.getStatus() != null){
            leaveResource.setStatusId(leave.getStatus().getId());
            leaveResource.setStatusCode(leave.getStatus().getCode());
        }

        if(leave.getLeaveType() != null){
            leaveResource.setLeaveTypeId(leave.getLeaveType().getId());
            leaveResource.setLeaveTypeCode(leave.getLeaveType().getCode());
        }

        if(leave.getLeaveTrackList() != null && !leave.getLeaveTrackList().isEmpty()){
            leaveResource.setLeaveTrackResourceList(LeaveTrackResource.toResource(leave.getLeaveTrackList()));
        }

        return leaveResource;
    }

    public static List<LeaveResource> toResource(List<Leave> leaveList){
        List<LeaveResource> leaveResourceList = new ArrayList<>();
        leaveList.forEach(leave -> {
            LeaveResource leaveResource = toResource(leave);
            leaveResourceList.add(leaveResource);
        });
        return leaveResourceList;
    }

    public Leave toLeave(){
        Leave leave = new Leave();
        leave.setId(this.id);
        leave.setNumber(this.number);
        leave.setEmployeeNumber(this.employeeNumber);
        leave.setCreateDate(this.createDate);
        leave.setReceiveDate(this.receiveDate);
        leave.setStartDate(this.startDate);
        leave.setEndDate(this.endDate);
        leave.setNumberOfDays(this.numberOfDays);

        if(this.statusId != null){
            Status status = new Status();
            status.setId(this.statusId);
            status.setCode(this.statusCode);
            leave.setStatus(status);
        }

        if(this.leaveTypeId != null){
            LeaveType leaveType = new LeaveType();
            leaveType.setId(this.leaveTypeId);
            leaveType.setCode(this.leaveTypeCode);
            leave.setLeaveType(leaveType);
        }

        if(this.leaveTrackResourceList != null && !this.leaveTrackResourceList.isEmpty()){
            List<LeaveTrack> leaveTrackList = new ArrayList<>();
            this.leaveTrackResourceList.forEach(leaveTrackResource -> {
                leaveTrackList.add(leaveTrackResource.toLeaveTrack());
            });
            leave.setLeaveTrackList(leaveTrackList);
        }

        return leave;
    }
}
