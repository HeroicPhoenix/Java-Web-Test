package com.lvwyh.controller;

import com.lvwyh.ao.DataArchiveRequestAO;
import com.lvwyh.ao.DataBackupPlanAO;
import com.lvwyh.ao.DataDeletionRequestAO;
import com.lvwyh.ao.DataStorageOptimizationAO;
import com.lvwyh.ao.MarketingAssetCatalogAO;
import com.lvwyh.service.DataAssetOptimizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data-asset-optimization")
@Tag(name = "数据资产优化", description = "营销数据资产目录及优化管理接口")
public class DataAssetOptimizationController {

    @Autowired
    private DataAssetOptimizationService dataAssetOptimizationService;

    @PostMapping("/catalog")
    @Operation(summary = "营销数据资产目录")
    public Map<String, Object> marketingAssetCatalog(@RequestBody MarketingAssetCatalogAO request,
                                                     HttpServletResponse response) {
        if (request == null || request.getPageNo() == null || request.getPageNo() <= 0
                || request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 200) {
            return buildError(response, "分页参数不合法");
        }
        return dataAssetOptimizationService.getMarketingAssetCatalog(request);
    }

    @PostMapping("/storage")
    @Operation(summary = "数据资产优化管理-数据存储")
    public Map<String, Object> optimizeStorage(@RequestBody DataStorageOptimizationAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getAssetId() == null || request.getAssetId() <= 0
                || request.getStorageLevel() == null || request.getStorageLevel().trim().length() == 0
                || request.getOperator() == null || request.getOperator().trim().length() == 0) {
            return buildError(response, "存储优化入参不合法");
        }
        return dataAssetOptimizationService.optimizeStorage(request);
    }

    @PostMapping("/backup")
    @Operation(summary = "数据资产优化管理-数据备份")
    public Map<String, Object> createBackupPlan(@RequestBody DataBackupPlanAO request,
                                                HttpServletResponse response) {
        if (request == null || request.getAssetId() == null || request.getAssetId() <= 0
                || request.getBackupStrategy() == null || request.getBackupStrategy().trim().length() == 0
                || request.getScheduleTime() == null || request.getScheduleTime().trim().length() == 0) {
            return buildError(response, "备份计划入参不合法");
        }
        return dataAssetOptimizationService.createBackupPlan(request);
    }

    @PostMapping("/archive")
    @Operation(summary = "数据资产优化管理-数据归档")
    public Map<String, Object> archiveAsset(@RequestBody DataArchiveRequestAO request,
                                            HttpServletResponse response) {
        if (request == null || request.getAssetId() == null || request.getAssetId() <= 0
                || request.getArchiveReason() == null || request.getArchiveReason().trim().length() < 5) {
            return buildError(response, "归档入参不合法");
        }
        return dataAssetOptimizationService.archiveAsset(request);
    }

    @PostMapping("/delete")
    @Operation(summary = "数据资产优化管理-数据删除")
    public Map<String, Object> deleteAsset(@RequestBody DataDeletionRequestAO request,
                                           HttpServletResponse response) {
        if (request == null || request.getAssetId() == null || request.getAssetId() <= 0
                || request.getReason() == null || request.getReason().trim().length() < 5
                || request.getApprovalUser() == null || request.getApprovalUser().trim().length() == 0) {
            return buildError(response, "删除入参不合法");
        }
        return dataAssetOptimizationService.deleteAsset(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
