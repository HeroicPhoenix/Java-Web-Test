package com.lvwyh.entity;

import java.util.Date;

public class IndicatorComparisonRecord {
    private Long id;
    private String indicatorCode;
    private String compareType;
    private String currentPeriod;
    private String previousPeriod;
    private Double currentValue;
    private Double previousValue;
    private Double differenceRate;
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

    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }

    public String getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public String getPreviousPeriod() {
        return previousPeriod;
    }

    public void setPreviousPeriod(String previousPeriod) {
        this.previousPeriod = previousPeriod;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(Double previousValue) {
        this.previousValue = previousValue;
    }

    public Double getDifferenceRate() {
        return differenceRate;
    }

    public void setDifferenceRate(Double differenceRate) {
        this.differenceRate = differenceRate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
