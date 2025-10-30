package com.lvwyh.entity;

import java.util.Date;

public class IndicatorSnapshot {
    private Long id;
    private String indicatorCode;
    private String indicatorName;
    private Date snapshotDate;
    private Double snapshotValue;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public Double getSnapshotValue() {
        return snapshotValue;
    }

    public void setSnapshotValue(Double snapshotValue) {
        this.snapshotValue = snapshotValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
