package com.lvwyh.ao;

public class DataBackupPlanAO {
    private Long assetId;
    private String backupStrategy;
    private String scheduleTime;
    private String operator;

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

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
