package com.lvwyh.entity;

import java.util.Date;

public class ElectricityUsageRecord {
    private Long id;
    private Long userId;
    private String monthTag;
    private Double usageKwh;
    private Double costAmount;
    private Date recordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMonthTag() {
        return monthTag;
    }

    public void setMonthTag(String monthTag) {
        this.monthTag = monthTag;
    }

    public Double getUsageKwh() {
        return usageKwh;
    }

    public void setUsageKwh(Double usageKwh) {
        this.usageKwh = usageKwh;
    }

    public Double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
