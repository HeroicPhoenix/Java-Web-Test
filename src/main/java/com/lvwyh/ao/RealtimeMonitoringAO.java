package com.lvwyh.ao;

public class RealtimeMonitoringAO {
    private String category;
    private Boolean includeAlerts;
    private Integer pageNo;
    private Integer pageSize;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIncludeAlerts() {
        return includeAlerts;
    }

    public void setIncludeAlerts(Boolean includeAlerts) {
        this.includeAlerts = includeAlerts;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
