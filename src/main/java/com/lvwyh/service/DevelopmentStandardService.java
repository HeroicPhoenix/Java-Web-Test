package com.lvwyh.service;

import com.lvwyh.ao.CodingStandardCheckAO;
import com.lvwyh.ao.FieldTypeStandardCheckAO;
import com.lvwyh.ao.IntegrationStandardCheckAO;
import com.lvwyh.ao.LayeringStandardCheckAO;
import com.lvwyh.ao.LineageStandardCheckAO;
import com.lvwyh.ao.SchedulingStandardCheckAO;
import com.lvwyh.ao.TableStandardCheckAO;

import java.util.Map;

public interface DevelopmentStandardService {
    Map<String, Object> checkTableStandard(TableStandardCheckAO request);

    Map<String, Object> checkFieldTypeStandard(FieldTypeStandardCheckAO request);

    Map<String, Object> checkLineageStandard(LineageStandardCheckAO request);

    Map<String, Object> checkCodingStandard(CodingStandardCheckAO request);

    Map<String, Object> checkLayeringStandard(LayeringStandardCheckAO request);

    Map<String, Object> checkSchedulingStandard(SchedulingStandardCheckAO request);

    Map<String, Object> checkIntegrationStandard(IntegrationStandardCheckAO request);
}
