package com.lvwyh.entity;

import java.util.Date;

public class IndicatorRealtimeSummary {
    private Long id;
    private String indicatorCategory;
    private Integer totalIndicators;
    private Integer activeAlerts;
    private String responsibleOwner;
    private Integer systemCalls;
    private Integer accessCount;
    private Integer averageResponseMs;
    private Date lastUpdatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicatorCategory() {
        return indicatorCategory;
    }

    public void setIndicatorCategory(String indicatorCategory) {
        this.indicatorCategory = indicatorCategory;
    }

    public Integer getTotalIndicators() {
        return totalIndicators;
    }

    public void setTotalIndicators(Integer totalIndicators) {
        this.totalIndicators = totalIndicators;
    }

    public Integer getActiveAlerts() {
        return activeAlerts;
    }

    public void setActiveAlerts(Integer activeAlerts) {
        this.activeAlerts = activeAlerts;
    }

    public String getResponsibleOwner() {
        return responsibleOwner;
    }

    public void setResponsibleOwner(String responsibleOwner) {
        this.responsibleOwner = responsibleOwner;
    }

    public Integer getSystemCalls() {
        return systemCalls;
    }

    public void setSystemCalls(Integer systemCalls) {
        this.systemCalls = systemCalls;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Integer getAverageResponseMs() {
        return averageResponseMs;
    }

    public void setAverageResponseMs(Integer averageResponseMs) {
        this.averageResponseMs = averageResponseMs;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
