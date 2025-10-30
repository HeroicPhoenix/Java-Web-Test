package com.lvwyh.entity;

import java.util.Date;

public class DatabaseMonitoringRecord {
    private Long id;
    private Date monitorDate;
    private Integer slowQueryCount;
    private Integer maxResponseTime;
    private String notes;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    public Integer getSlowQueryCount() {
        return slowQueryCount;
    }

    public void setSlowQueryCount(Integer slowQueryCount) {
        this.slowQueryCount = slowQueryCount;
    }

    public Integer getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(Integer maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
