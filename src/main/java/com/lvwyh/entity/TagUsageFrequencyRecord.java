package com.lvwyh.entity;

import java.util.Date;

public class TagUsageFrequencyRecord {
    private Long id;
    private String tagName;
    private String frequencyType;
    private Double averageFrequency;
    private String referencePeriod;
    private Date calculatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Double getAverageFrequency() {
        return averageFrequency;
    }

    public void setAverageFrequency(Double averageFrequency) {
        this.averageFrequency = averageFrequency;
    }

    public String getReferencePeriod() {
        return referencePeriod;
    }

    public void setReferencePeriod(String referencePeriod) {
        this.referencePeriod = referencePeriod;
    }

    public Date getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(Date calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
