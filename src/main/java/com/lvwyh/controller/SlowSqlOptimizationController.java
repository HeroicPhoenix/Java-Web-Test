package com.lvwyh.controller;

import com.lvwyh.ao.DatabaseMonitoringRecordAO;
import com.lvwyh.ao.SlowQueryClassificationAO;
import com.lvwyh.ao.StructureAdjustmentStrategyAO;
import com.lvwyh.service.SlowSqlOptimizationService;
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
@RequestMapping("/api/slow-sql")
@Tag(name = "慢SQL问题优化记录", description = "慢SQL分类、结构调整和监控记录接口")
public class SlowSqlOptimizationController {

    private final SlowSqlOptimizationService slowSqlOptimizationService;

    public SlowSqlOptimizationController(SlowSqlOptimizationService slowSqlOptimizationService) {
        this.slowSqlOptimizationService = slowSqlOptimizationService;
    }

    @PostMapping("/classify")
    @Operation(summary = "分类管理查询语句")
    public Map<String, Object> classifySlowQuery(@RequestBody SlowQueryClassificationAO request,
                                                 HttpServletResponse response) {
        if (request == null || request.getCategoryName() == null || request.getCategoryName().trim().length() == 0) {
            return buildError(response, "分类名称不能为空");
        }
        if (request.getSampleSql() == null || request.getSampleSql().trim().length() < 10) {
            return buildError(response, "示例SQL长度不足");
        }
        if (request.getExpectedGain() == null || request.getExpectedGain().intValue() < 0
                || request.getExpectedGain().intValue() > 100) {
            return buildError(response, "预期收益需在0-100之间");
        }
        return slowSqlOptimizationService.classifySlowQuery(request);
    }

    @PostMapping("/structure")
    @Operation(summary = "数据库结构调整策略")
    public Map<String, Object> registerStructure(@RequestBody StructureAdjustmentStrategyAO request,
                                                 HttpServletResponse response) {
        if (request == null || request.getStrategyName() == null || request.getStrategyName().trim().length() == 0) {
            return buildError(response, "策略名称不能为空");
        }
        if (request.getAdjustmentDetail() == null || request.getAdjustmentDetail().trim().length() < 10) {
            return buildError(response, "请提供详细的调整策略");
        }
        if (request.getExpectedImpact() == null || request.getExpectedImpact().trim().length() == 0) {
            return buildError(response, "预期影响不能为空");
        }
        return slowSqlOptimizationService.registerStructureStrategy(request);
    }

    @PostMapping("/monitoring")
    @Operation(summary = "建立数据库监控记录")
    public Map<String, Object> recordMonitoring(@RequestBody DatabaseMonitoringRecordAO request,
                                                HttpServletResponse response) {
        if (request == null || request.getMonitorDate() == null || request.getMonitorDate().trim().length() == 0) {
            return buildError(response, "监控日期不能为空");
        }
        if (request.getSlowQueryCount() == null || request.getSlowQueryCount().intValue() < 0) {
            return buildError(response, "慢SQL数量不能为负");
        }
        if (request.getMaxResponseTime() == null || request.getMaxResponseTime().intValue() <= 0) {
            return buildError(response, "最大响应时间必须大于0");
        }
        return slowSqlOptimizationService.recordMonitoring(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
