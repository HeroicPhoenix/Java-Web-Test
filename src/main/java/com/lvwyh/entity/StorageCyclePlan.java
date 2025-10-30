package com.lvwyh.entity;

import java.util.Date;

public class StorageCyclePlan {
    private Long id;
    private String datasetName;
    private String strategyType;
    private Integer recommendedDays;
    private String notes;
    private String requestedBy;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public Integer getRecommendedDays() {
        return recommendedDays;
    }

    public void setRecommendedDays(Integer recommendedDays) {
        this.recommendedDays = recommendedDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
