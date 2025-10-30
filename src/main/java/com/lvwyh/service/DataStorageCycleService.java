package com.lvwyh.service;

import com.lvwyh.ao.DataLifecycleRecordAO;
import com.lvwyh.ao.HighSensitivityStoragePlanAO;
import com.lvwyh.ao.LongTermStoragePlanAO;

import java.util.Map;

public interface DataStorageCycleService {
    Map<String, Object> recordLifecycle(DataLifecycleRecordAO request);

    Map<String, Object> planHighSensitivityStorage(HighSensitivityStoragePlanAO request);

    Map<String, Object> planLongTermStorage(LongTermStoragePlanAO request);
}
