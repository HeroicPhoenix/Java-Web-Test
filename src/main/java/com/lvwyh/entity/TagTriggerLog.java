package com.lvwyh.entity;

import java.util.Date;

public class TagTriggerLog {
    private Long id;
    private String tagName;
    private Integer triggerCount;
    private Double successRate;
    private String monitoringWindow;
    private Date monitoredDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTriggerCount() {
        return triggerCount;
    }

    public void setTriggerCount(Integer triggerCount) {
        this.triggerCount = triggerCount;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public String getMonitoringWindow() {
        return monitoringWindow;
    }

    public void setMonitoringWindow(String monitoringWindow) {
        this.monitoringWindow = monitoringWindow;
    }

    public Date getMonitoredDate() {
        return monitoredDate;
    }

    public void setMonitoredDate(Date monitoredDate) {
        this.monitoredDate = monitoredDate;
    }
}
