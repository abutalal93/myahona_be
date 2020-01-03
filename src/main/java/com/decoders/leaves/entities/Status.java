package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STATUS")
public class Status implements Serializable {

    @Id
    @SequenceGenerator(name = "StatusSeq", sequenceName = "STATUS_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "StatusSeq", strategy = GenerationType.AUTO)
    @Column(name = "sts_id" , updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "sts_code")
    private String code;

    @Column(name = "sts_desc")
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
