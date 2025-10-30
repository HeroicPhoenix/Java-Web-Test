package com.lvwyh.entity;

import java.util.Date;

public class AssetBackupPlan {
    private Long id;
    private Long assetId;
    private String backupStrategy;
    private Date scheduleTime;
    private String operator;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getBackupStrategy() {
        return backupStrategy;
    }

    public void setBackupStrategy(String backupStrategy) {
        this.backupStrategy = backupStrategy;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
