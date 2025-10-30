package com.lvwyh.controller;

import com.lvwyh.ao.DataLifecycleRecordAO;
import com.lvwyh.ao.HighSensitivityStoragePlanAO;
import com.lvwyh.ao.LongTermStoragePlanAO;
import com.lvwyh.service.DataStorageCycleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data-storage-cycle")
@Tag(name = "数据存储周期记录", description = "数据生命周期记录与存储策略接口")
public class DataStorageCycleController {

    private final DataStorageCycleService dataStorageCycleService;

    public DataStorageCycleController(DataStorageCycleService dataStorageCycleService) {
        this.dataStorageCycleService = dataStorageCycleService;
    }

    @PostMapping("/lifecycle")
    @Operation(summary = "记录数据生命周期")
    public Map<String, Object> recordLifecycle(@RequestBody DataLifecycleRecordAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getRetentionDays() == null || request.getRetentionDays().intValue() <= 0) {
            return buildError(response, "保留天数必须大于0");
        }
        if (request.getOwner() == null || request.getOwner().trim().length() == 0) {
            return buildError(response, "负责人不能为空");
        }
        return dataStorageCycleService.recordLifecycle(request);
    }

    @PostMapping("/plan/high-sensitivity")
    @Operation(summary = "数据存储周期规划-时效性较强的数据，适当缩短存储周期")
    public Map<String, Object> planHighSensitivity(@RequestBody HighSensitivityStoragePlanAO request,
                                                   HttpServletResponse response) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getRecommendedDays() == null || request.getRecommendedDays().intValue() <= 0
                || request.getRecommendedDays().intValue() > 90) {
            return buildError(response, "推荐天数范围应在1-90天");
        }
        if (request.getJustification() == null || request.getJustification().trim().length() < 5) {
            return buildError(response, "缩短理由需要至少5个字符");
        }
        if (request.getRequestedBy() == null || request.getRequestedBy().trim().length() == 0) {
            return buildError(response, "申请人不能为空");
        }
        return dataStorageCycleService.planHighSensitivityStorage(request);
    }

    @PostMapping("/plan/long-term")
    @Operation(summary = "数据存储周期规划-长期业务数据，考虑长期存储策略")
    public Map<String, Object> planLongTerm(@RequestBody LongTermStoragePlanAO request,
                                            HttpServletResponse response) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getMinimumRetentionDays() == null || request.getMinimumRetentionDays().intValue() < 180) {
            return buildError(response, "长期存储至少需要180天");
        }
        if (request.getArchivalStrategy() == null || request.getArchivalStrategy().trim().length() < 10) {
            return buildError(response, "请提供详细的归档策略");
        }
        if (request.getReviewer() == null || request.getReviewer().trim().length() == 0) {
            return buildError(response, "审核人不能为空");
        }
        return dataStorageCycleService.planLongTermStorage(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
