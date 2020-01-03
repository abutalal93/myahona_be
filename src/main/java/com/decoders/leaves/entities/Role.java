package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    @Id
    @SequenceGenerator(name = "RoleSeq", sequenceName = "ROLE_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "RoleSeq", strategy = GenerationType.AUTO)
    @Column(name = "rl_id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "rl_code")
    private String code;

    @Column(name = "rl_desc")
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
}
