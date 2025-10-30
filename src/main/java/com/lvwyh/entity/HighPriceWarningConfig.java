package com.lvwyh.entity;

public class HighPriceWarningConfig {
    private Long id;
    private Double costThreshold;
    private Double usageThreshold;
    private String strategyDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCostThreshold() {
        return costThreshold;
    }

    public void setCostThreshold(Double costThreshold) {
        this.costThreshold = costThreshold;
    }

    public Double getUsageThreshold() {
        return usageThreshold;
    }

    public void setUsageThreshold(Double usageThreshold) {
        this.usageThreshold = usageThreshold;
    }

    public String getStrategyDescription() {
        return strategyDescription;
    }

    public void setStrategyDescription(String strategyDescription) {
        this.strategyDescription = strategyDescription;
    }
}
