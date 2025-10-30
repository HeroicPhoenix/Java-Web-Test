package com.lvwyh.service;

import com.lvwyh.ao.IndicatorComparisonMoMAO;
import com.lvwyh.ao.IndicatorComparisonYoYAO;
import com.lvwyh.ao.IndicatorHistoryQueryAO;
import com.lvwyh.ao.IndicatorTrendDailyAO;
import com.lvwyh.ao.IndicatorTrendMonthlyAO;
import com.lvwyh.ao.IndicatorTrendWeeklyAO;
import com.lvwyh.ao.RealtimeMonitoringAO;

import java.util.Map;

public interface MarketingIndicatorMonitoringService {
    Map<String, Object> queryIndicatorHistory(IndicatorHistoryQueryAO request);

    Map<String, Object> analyzeDailyTrend(IndicatorTrendDailyAO request);

    Map<String, Object> analyzeWeeklyTrend(IndicatorTrendWeeklyAO request);

    Map<String, Object> analyzeMonthlyTrend(IndicatorTrendMonthlyAO request);

    Map<String, Object> compareYearOverYear(IndicatorComparisonYoYAO request);

    Map<String, Object> compareMonthOverMonth(IndicatorComparisonMoMAO request);

    Map<String, Object> realtimeMonitoring(RealtimeMonitoringAO request);
}
