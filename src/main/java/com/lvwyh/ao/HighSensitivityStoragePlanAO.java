package com.lvwyh.ao;

public class HighSensitivityStoragePlanAO {
    private String datasetName;
    private Integer recommendedDays;
    private String justification;
    private String requestedBy;

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Integer getRecommendedDays() {
        return recommendedDays;
    }

    public void setRecommendedDays(Integer recommendedDays) {
        this.recommendedDays = recommendedDays;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
