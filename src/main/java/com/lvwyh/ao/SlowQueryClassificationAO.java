package com.lvwyh.ao;

public class SlowQueryClassificationAO {
    private String categoryName;
    private String sampleSql;
    private String optimizationSuggestion;
    private Integer expectedGain;

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
}
