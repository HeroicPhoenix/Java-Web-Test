package com.lvwyh.ao;

public class DataLifecycleRecordAO {
    private String datasetName;
    private String businessProcess;
    private Integer retentionDays;
    private String owner;

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }

    public Integer getRetentionDays() {
        return retentionDays;
    }

    public void setRetentionDays(Integer retentionDays) {
        this.retentionDays = retentionDays;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
