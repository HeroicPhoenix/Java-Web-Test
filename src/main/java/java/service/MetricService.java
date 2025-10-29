package java.service;

import java.dto.MetricRequest;
import java.entity.MetricRecord;
import java.util.List;

public interface MetricService {

    MetricRecord recordHistoricalMetric(MetricRequest request);

    List<MetricRecord> analyzeDailyTrend(MetricRequest request);

    List<MetricRecord> analyzeWeeklyTrend(MetricRequest request);

    List<MetricRecord> analyzeMonthlyTrend(MetricRequest request);

    List<MetricRecord> compareYearOverYear(MetricRequest request);

    List<MetricRecord> compareMonthOverMonth(MetricRequest request);

    List<MetricRecord> monitorRealTime(MetricRequest request);
}
