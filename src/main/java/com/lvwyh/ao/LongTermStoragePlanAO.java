package com.lvwyh.ao;

public class LongTermStoragePlanAO {
    private String datasetName;
    private Integer minimumRetentionDays;
    private String archivalStrategy;
    private String reviewer;

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Integer getMinimumRetentionDays() {
        return minimumRetentionDays;
    }

    public void setMinimumRetentionDays(Integer minimumRetentionDays) {
        this.minimumRetentionDays = minimumRetentionDays;
    }

    public String getArchivalStrategy() {
        return archivalStrategy;
    }

    public void setArchivalStrategy(String archivalStrategy) {
        this.archivalStrategy = archivalStrategy;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
