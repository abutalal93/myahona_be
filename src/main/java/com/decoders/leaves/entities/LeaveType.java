package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LEAVE_TYPE")
public class LeaveType implements Serializable {

    @Id
    @SequenceGenerator(name = "LeaveSeq", sequenceName = "LEAVE_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "LeaveSeq", strategy = GenerationType.AUTO)
    @Column(name = "lev_typ_id" , updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "lev_typ_code")
    private String code;

    @Column(name = "lev_typ_desc")
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
