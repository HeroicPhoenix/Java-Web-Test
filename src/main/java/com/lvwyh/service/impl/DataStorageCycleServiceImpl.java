package com.lvwyh.service.impl;

import com.lvwyh.ao.DataLifecycleRecordAO;
import com.lvwyh.ao.HighSensitivityStoragePlanAO;
import com.lvwyh.ao.LongTermStoragePlanAO;
import com.lvwyh.entity.DataLifecycleRecord;
import com.lvwyh.entity.StorageCyclePlan;
import com.lvwyh.mapper.DataStorageCycleMapper;
import com.lvwyh.service.DataStorageCycleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataStorageCycleServiceImpl implements DataStorageCycleService {

    private final DataStorageCycleMapper dataStorageCycleMapper;

    public DataStorageCycleServiceImpl(DataStorageCycleMapper dataStorageCycleMapper) {
        this.dataStorageCycleMapper = dataStorageCycleMapper;
    }

    @Override
    public Map<String, Object> recordLifecycle(DataLifecycleRecordAO request) {
        DataLifecycleRecord record = new DataLifecycleRecord();
        record.setDatasetName(request.getDatasetName());
        record.setBusinessProcess(request.getBusinessProcess());
        record.setRetentionDays(request.getRetentionDays());
        record.setOwner(request.getOwner());
        record.setCreatedAt(new Date());
        dataStorageCycleMapper.insertLifecycleRecord(record);
        List<DataLifecycleRecord> records = dataStorageCycleMapper.selectLifecycleRecords(request.getDatasetName());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("records", records);
        return result;
    }

    @Override
    public Map<String, Object> planHighSensitivityStorage(HighSensitivityStoragePlanAO request) {
        StorageCyclePlan plan = new StorageCyclePlan();
        plan.setDatasetName(request.getDatasetName());
        plan.setStrategyType("HIGH_SENSITIVITY");
        plan.setRecommendedDays(request.getRecommendedDays());
        plan.setNotes(request.getJustification());
        plan.setRequestedBy(request.getRequestedBy());
        plan.setCreatedAt(new Date());
        dataStorageCycleMapper.insertStoragePlan(plan);
        List<StorageCyclePlan> plans = dataStorageCycleMapper.selectPlansByType(request.getDatasetName(), "HIGH_SENSITIVITY");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("plans", plans);
        return result;
    }

    @Override
    public Map<String, Object> planLongTermStorage(LongTermStoragePlanAO request) {
        StorageCyclePlan plan = new StorageCyclePlan();
        plan.setDatasetName(request.getDatasetName());
        plan.setStrategyType("LONG_TERM");
        plan.setRecommendedDays(request.getMinimumRetentionDays());
        plan.setNotes(request.getArchivalStrategy());
        plan.setRequestedBy(request.getReviewer());
        plan.setCreatedAt(new Date());
        dataStorageCycleMapper.insertStoragePlan(plan);
        List<StorageCyclePlan> plans = dataStorageCycleMapper.selectPlansByType(request.getDatasetName(), "LONG_TERM");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("plans", plans);
        return result;
    }
}
