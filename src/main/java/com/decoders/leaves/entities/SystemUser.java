package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SYSTEM_USER")
public class SystemUser implements Serializable {

    @Id
    @SequenceGenerator(name = "SystemUserSeq", sequenceName = "SYSTEM_USER_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SystemUserSeq", strategy = GenerationType.AUTO)
    @Column(name = "sys_id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "sys_username")
    private String username;

    @Column(name = "sys_password")
    private String password;

    @Column(name = "sys_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "sys_expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "sys_first_name")
    private String firstName;

    @Column(name = "sys_last_name")
    private String lastName;

    @Column(name = "sys_email")
    private String email;

    @Column(name = "sys_mobile")
    private String mobile;

    @Column(name = "sys_address")
    private String address;

    @OneToOne
    @JoinColumn(name = "sts_id" , referencedColumnName = "sts_id")
    private Status status;


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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
