package com.lvwyh.entity;

import java.util.Date;

public class SlowQueryOptimization {
    private Long id;
    private String categoryName;
    private String sampleSql;
    private String optimizationSuggestion;
    private Integer expectedGain;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSampleSql() {
        return sampleSql;
    }

    public void setSampleSql(String sampleSql) {
        this.sampleSql = sampleSql;
    }

    public String getOptimizationSuggestion() {
        return optimizationSuggestion;
    }

    public void setOptimizationSuggestion(String optimizationSuggestion) {
        this.optimizationSuggestion = optimizationSuggestion;
    }

    public Integer getExpectedGain() {
        return expectedGain;
    }

    public void setExpectedGain(Integer expectedGain) {
        this.expectedGain = expectedGain;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
