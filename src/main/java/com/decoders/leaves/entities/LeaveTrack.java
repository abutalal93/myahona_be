package com.decoders.leaves.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "LEAVE_TRACK")
public class LeaveTrack implements Serializable {

    @Id
    @SequenceGenerator(name = "LeaveTrackSeq", sequenceName = "LEAVE_TRACK_SEQ"
            , initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "LeaveSeq", strategy = GenerationType.AUTO)
    @Column(name = "lev_trk_id" , updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "lev_trk_text")
    private String text;

    @Column(name = "lev_trk_create_date")
    private LocalDateTime createDate;

    @OneToOne
    @JoinColumn(name = "lev_id", nullable = false)
    private Leave leave;

    @OneToOne
    @JoinColumn(name = "sts_id", nullable = false)
    private Status status;

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

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
