package com.lvwyh.entity;

import java.util.Date;

public class DataMaskingRule {
    private Long id;
    private String datasetName;
    private String fieldName;
    private String maskingRule;
    private String sample;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
