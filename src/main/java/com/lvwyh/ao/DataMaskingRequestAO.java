package com.lvwyh.ao;

public class DataMaskingRequestAO {
    private String datasetName;
    private String fieldName;
    private String maskingRule;
    private String sample;

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMaskingRule() {
        return maskingRule;
    }

    public void setMaskingRule(String maskingRule) {
        this.maskingRule = maskingRule;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
