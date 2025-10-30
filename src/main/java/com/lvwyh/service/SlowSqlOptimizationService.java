package com.lvwyh.service;

import com.lvwyh.ao.DatabaseMonitoringRecordAO;
import com.lvwyh.ao.SlowQueryClassificationAO;
import com.lvwyh.ao.StructureAdjustmentStrategyAO;

import java.util.Map;

public interface SlowSqlOptimizationService {
    Map<String, Object> classifySlowQuery(SlowQueryClassificationAO request);

    Map<String, Object> registerStructureStrategy(StructureAdjustmentStrategyAO request);

    Map<String, Object> recordMonitoring(DatabaseMonitoringRecordAO request);
}
