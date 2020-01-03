package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYSTEM_USER_ROLE")
public class SystemUserRole implements Serializable {

    @Id
    @SequenceGenerator(name = "SystemUserRoleSeq", sequenceName = "SYSTEM_USER_ROLE_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SystemUserRoleSeq", strategy = GenerationType.AUTO)
    @Column(name = "sys_rl_id", updatable = false, nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sys_id", referencedColumnName = "sys_id")
    private SystemUser systemUser;

    @ManyToOne
    @JoinColumn(name = "rl_id", referencedColumnName = "rl_id")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
