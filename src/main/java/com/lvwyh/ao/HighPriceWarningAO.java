package com.lvwyh.ao;

public class HighPriceWarningAO {
    private Long userId;
    private Double forecastCost;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getForecastCost() {
        return forecastCost;
    }

    public void setForecastCost(Double forecastCost) {
        this.forecastCost = forecastCost;
    }
}
