package com.lvwyh.service.impl;

import com.lvwyh.ao.IndicatorComparisonMoMAO;
import com.lvwyh.ao.IndicatorComparisonYoYAO;
import com.lvwyh.ao.IndicatorHistoryQueryAO;
import com.lvwyh.ao.IndicatorTrendDailyAO;
import com.lvwyh.ao.IndicatorTrendMonthlyAO;
import com.lvwyh.ao.IndicatorTrendWeeklyAO;
import com.lvwyh.ao.RealtimeMonitoringAO;
import com.lvwyh.entity.IndicatorComparisonRecord;
import com.lvwyh.entity.IndicatorRealtimeSummary;
import com.lvwyh.entity.IndicatorSnapshot;
import com.lvwyh.entity.IndicatorTrendRecord;
import com.lvwyh.mapper.MarketingIndicatorMonitoringMapper;
import com.lvwyh.service.MarketingIndicatorMonitoringService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketingIndicatorMonitoringServiceImpl implements MarketingIndicatorMonitoringService {

    private final MarketingIndicatorMonitoringMapper marketingIndicatorMonitoringMapper;

    public MarketingIndicatorMonitoringServiceImpl(MarketingIndicatorMonitoringMapper marketingIndicatorMonitoringMapper) {
        this.marketingIndicatorMonitoringMapper = marketingIndicatorMonitoringMapper;
    }

    @Override
    public Map<String, Object> queryIndicatorHistory(IndicatorHistoryQueryAO request) {
        List<IndicatorSnapshot> snapshots = marketingIndicatorMonitoringMapper.selectIndicatorSnapshots(
                request.getIndicatorCode(), request.getStartDate(), request.getEndDate());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("snapshots", snapshots);
        return result;
    }

    @Override
    public Map<String, Object> analyzeDailyTrend(IndicatorTrendDailyAO request) {
        List<IndicatorTrendRecord> records = marketingIndicatorMonitoringMapper.selectTrendRecords(
                request.getIndicatorCode(), "DAY", request.getRecentDays().intValue());
        Map<String, Object> result = buildTrendResponse(records);
        result.put("periodType", "DAY");
        return result;
    }

    @Override
    public Map<String, Object> analyzeWeeklyTrend(IndicatorTrendWeeklyAO request) {
        List<IndicatorTrendRecord> records = marketingIndicatorMonitoringMapper.selectTrendRecords(
                request.getIndicatorCode(), "WEEK", request.getRecentWeeks().intValue());
        Map<String, Object> result = buildTrendResponse(records);
        result.put("periodType", "WEEK");
        return result;
    }

    @Override
    public Map<String, Object> analyzeMonthlyTrend(IndicatorTrendMonthlyAO request) {
        List<IndicatorTrendRecord> records = marketingIndicatorMonitoringMapper.selectTrendRecords(
                request.getIndicatorCode(), "MONTH", request.getRecentMonths().intValue());
        Map<String, Object> result = buildTrendResponse(records);
        result.put("periodType", "MONTH");
        return result;
    }

    @Override
    public Map<String, Object> compareYearOverYear(IndicatorComparisonYoYAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        LocalDate currentDate = LocalDate.parse(request.getCurrentPeriod());
        LocalDate previousDate = LocalDate.parse(request.getPreviousPeriod());
        Double currentValue = fetchSnapshotValue(request.getIndicatorCode(), currentDate);
        Double previousValue = fetchSnapshotValue(request.getIndicatorCode(), previousDate);
        if (currentValue == null || previousValue == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "未找到指定日期的指标快照数据");
            return result;
        }
        IndicatorComparisonRecord record = new IndicatorComparisonRecord();
        record.setIndicatorCode(request.getIndicatorCode());
        record.setCompareType("YOY");
        record.setCurrentPeriod(currentDate.toString());
        record.setPreviousPeriod(previousDate.toString());
        record.setCurrentValue(currentValue);
        record.setPreviousValue(previousValue);
        if (previousValue.doubleValue() != 0) {
            double rate = (currentValue.doubleValue() - previousValue.doubleValue()) / previousValue.doubleValue();
            record.setDifferenceRate(rate);
        } else {
            record.setDifferenceRate(null);
        }
        record.setCreatedAt(new Date());
        result.put("success", Boolean.TRUE);
        result.put("comparison", record);
        return result;
    }

    @Override
    public Map<String, Object> compareMonthOverMonth(IndicatorComparisonMoMAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        LocalDate currentDate = LocalDate.parse(request.getCurrentPeriod());
        LocalDate previousDate = LocalDate.parse(request.getPreviousPeriod());
        Double currentValue = fetchSnapshotValue(request.getIndicatorCode(), currentDate);
        Double previousValue = fetchSnapshotValue(request.getIndicatorCode(), previousDate);
        if (currentValue == null || previousValue == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "未找到指定日期的指标快照数据");
            return result;
        }
        IndicatorComparisonRecord record = new IndicatorComparisonRecord();
        record.setIndicatorCode(request.getIndicatorCode());
        record.setCompareType("MOM");
        record.setCurrentPeriod(currentDate.toString());
        record.setPreviousPeriod(previousDate.toString());
        record.setCurrentValue(currentValue);
        record.setPreviousValue(previousValue);
        if (previousValue.doubleValue() != 0) {
            double rate = (currentValue.doubleValue() - previousValue.doubleValue()) / previousValue.doubleValue();
            record.setDifferenceRate(rate);
        } else {
            record.setDifferenceRate(null);
        }
        record.setCreatedAt(new Date());
        result.put("success", Boolean.TRUE);
        result.put("comparison", record);
        return result;
    }

    @Override
    public Map<String, Object> realtimeMonitoring(RealtimeMonitoringAO request) {
        int pageNo = request.getPageNo().intValue();
        int pageSize = request.getPageSize().intValue();
        boolean includeAlerts = request.getIncludeAlerts() != null && request.getIncludeAlerts().booleanValue();
        List<IndicatorRealtimeSummary> summaries = marketingIndicatorMonitoringMapper.selectRealtimeSummary(
                request.getCategory(), includeAlerts, (pageNo - 1) * pageSize, pageSize);
        int total = marketingIndicatorMonitoringMapper.countRealtimeSummary(request.getCategory(), includeAlerts);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("total", total);
        result.put("summaries", summaries);
        return result;
    }

    private Map<String, Object> buildTrendResponse(List<IndicatorTrendRecord> records) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("records", records);
        double average = 0;
        if (records != null && !records.isEmpty()) {
            double sum = 0;
            for (IndicatorTrendRecord record : records) {
                if (record.getAverageValue() != null) {
                    sum += record.getAverageValue();
                }
            }
            average = sum / records.size();
        }
        result.put("average", Math.round(average * 100.0) / 100.0);
        return result;
    }

    private Double fetchSnapshotValue(String indicatorCode, LocalDate date) {
        List<IndicatorSnapshot> snapshots = marketingIndicatorMonitoringMapper.selectIndicatorSnapshots(
                indicatorCode, date.toString(), date.toString());
        if (snapshots == null || snapshots.isEmpty()) {
            return null;
        }
        IndicatorSnapshot snapshot = snapshots.get(snapshots.size() - 1);
        return snapshot.getSnapshotValue();
    }
}
