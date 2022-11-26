package com.martincastroalvarez.london;

import java.util.Date;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jobs")
public class Job {
    // --------------------------------------------------------------------
    // Job Model.
    //
    // This model represents a Job that is executed asynchronously.
    // --------------------------------------------------------------------

    enum Status {
        PENDING,
        STARTED,
        FAILED,
        ENDED,
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Date createdAt;
    private Date endedAt;
    private Date startedAt;
    private Status status;
    private String message;

    public void Job() {
        this.createdAt = new Date();
        this.status = Status.PENDING;
    }

    public void Job(Long id) {
        this.id = id;
        this.createdAt = new Date();
        this.status = Status.PENDING;
    }

    public Long getId() { return id; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getStartedAt() { return startedAt; }
    public void setStartedAt(Date startedAt) { this.startedAt = startedAt; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getEndedAt() { return endedAt; }
    public void setEndedAt(Date endedAt) { this.endedAt = endedAt; }

    public void end() {
        // --------------------------------------------------------------------
        // Transition job to the ENDED state.
        // --------------------------------------------------------------------
        this.setEndedAt(new Date());
        this.setStatus(Status.ENDED);
    }

    public void start() {
        // --------------------------------------------------------------------
        // Transition job to the STARTED state.
        // --------------------------------------------------------------------
        this.setStartedAt(new Date());
        this.setStatus(Status.STARTED);
    }

    public void fail(String message) {
        // --------------------------------------------------------------------
        // Transition job to the FAILED state.
        // --------------------------------------------------------------------
        this.setMessage(message);
        this.setEndedAt(new Date());
        this.setStatus(Status.FAILED);
    }

}
