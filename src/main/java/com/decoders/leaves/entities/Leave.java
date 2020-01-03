package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "LEAVE")
public class Leave implements Serializable {

    @Id
    @SequenceGenerator(name = "LeaveSeq", sequenceName = "LEAVE_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "LeaveSeq", strategy = GenerationType.AUTO)
    @Column(name = "lev_id" , updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "lev_number")
    private String number;

    @Column(name = "lev_emp_number")
    private String employeeNumber;

    @Column(name = "lev_create_date")
    private LocalDateTime createDate;

    @Column(name = "lev_receive_date")
    private LocalDateTime receiveDate;

    @Column(name = "lev_start_date")
    private LocalDateTime startDate;

    @Column(name = "lev_end_date")
    private LocalDateTime endDate;

    @Column(name = "lev_number_of_days")
    private Long numberOfDays;

    @OneToOne
    @JoinColumn(name = "sts_id", nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "lev_typ_id", nullable = false)
    private LeaveType leaveType;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}
