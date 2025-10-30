package com.lvwyh.ao;

public class IndicatorTrendDailyAO {
    private String indicatorCode;
    private Integer recentDays;

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public Integer getRecentDays() {
        return recentDays;
    }

    public void setRecentDays(Integer recentDays) {
        this.recentDays = recentDays;
    }
}
