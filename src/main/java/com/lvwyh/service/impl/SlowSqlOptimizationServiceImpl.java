package com.lvwyh.service.impl;

import com.lvwyh.ao.DatabaseMonitoringRecordAO;
import com.lvwyh.ao.SlowQueryClassificationAO;
import com.lvwyh.ao.StructureAdjustmentStrategyAO;
import com.lvwyh.entity.DatabaseMonitoringRecord;
import com.lvwyh.entity.DatabaseStructureStrategy;
import com.lvwyh.entity.SlowQueryOptimization;
import com.lvwyh.mapper.SlowSqlOptimizationMapper;
import com.lvwyh.service.SlowSqlOptimizationService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlowSqlOptimizationServiceImpl implements SlowSqlOptimizationService {

    private final SlowSqlOptimizationMapper slowSqlOptimizationMapper;

    public SlowSqlOptimizationServiceImpl(SlowSqlOptimizationMapper slowSqlOptimizationMapper) {
        this.slowSqlOptimizationMapper = slowSqlOptimizationMapper;
    }

    @Override
    public Map<String, Object> classifySlowQuery(SlowQueryClassificationAO request) {
        SlowQueryOptimization optimization = new SlowQueryOptimization();
        optimization.setCategoryName(request.getCategoryName());
        optimization.setSampleSql(request.getSampleSql());
        optimization.setOptimizationSuggestion(request.getOptimizationSuggestion());
        optimization.setExpectedGain(request.getExpectedGain());
        optimization.setCreatedAt(new Date());
        slowSqlOptimizationMapper.insertSlowQuery(optimization);
        List<SlowQueryOptimization> list = slowSqlOptimizationMapper.selectSlowQueries(request.getCategoryName());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("queries", list);
        return result;
    }

    @Override
    public Map<String, Object> registerStructureStrategy(StructureAdjustmentStrategyAO request) {
        DatabaseStructureStrategy strategy = new DatabaseStructureStrategy();
        strategy.setStrategyName(request.getStrategyName());
        strategy.setAdjustmentDetail(request.getAdjustmentDetail());
        strategy.setExpectedImpact(request.getExpectedImpact());
        strategy.setProposer(request.getProposer());
        strategy.setCreatedAt(new Date());
        slowSqlOptimizationMapper.insertStructureStrategy(strategy);
        List<DatabaseStructureStrategy> strategies = slowSqlOptimizationMapper.selectStructureStrategies();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("strategies", strategies);
        return result;
    }

    @Override
    public Map<String, Object> recordMonitoring(DatabaseMonitoringRecordAO request) {
        DatabaseMonitoringRecord record = new DatabaseMonitoringRecord();
        record.setSlowQueryCount(request.getSlowQueryCount());
        record.setMaxResponseTime(request.getMaxResponseTime());
        record.setNotes(request.getNotes());
        record.setCreatedAt(new Date());
        record.setMonitorDate(parseDate(request.getMonitorDate()));
        slowSqlOptimizationMapper.insertMonitoringRecord(record);
        List<DatabaseMonitoringRecord> records = slowSqlOptimizationMapper.selectMonitoringRecords(30);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("records", records);
        return result;
    }

    private Date parseDate(String value) {
        if (value == null) {
            return new Date();
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
