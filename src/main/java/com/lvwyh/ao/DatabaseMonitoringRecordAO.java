package com.lvwyh.ao;

public class DatabaseMonitoringRecordAO {
    private String monitorDate;
    private Integer slowQueryCount;
    private Integer maxResponseTime;
    private String notes;

    public String getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(String monitorDate) {
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
}
