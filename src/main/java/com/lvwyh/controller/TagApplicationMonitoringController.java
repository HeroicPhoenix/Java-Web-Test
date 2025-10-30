package com.lvwyh.controller;

import com.lvwyh.ao.TagEffectivenessAO;
import com.lvwyh.ao.TagImpactScopeAO;
import com.lvwyh.ao.TagTriggerStatusAO;
import com.lvwyh.ao.TagUsageCountAO;
import com.lvwyh.ao.TagUsageFrequencyAO;
import com.lvwyh.ao.TagUserFeedbackAO;
import com.lvwyh.service.TagApplicationMonitoringService;
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
@RequestMapping("/api/tag-monitoring")
@Tag(name = "标签应用运营监控", description = "营销标签应用监控接口")
public class TagApplicationMonitoringController {

    private final TagApplicationMonitoringService tagApplicationMonitoringService;

    @Autowired
    public TagApplicationMonitoringController(TagApplicationMonitoringService tagApplicationMonitoringService) {
        this.tagApplicationMonitoringService = tagApplicationMonitoringService;
    }

    @PostMapping("/usage-count")
    @Operation(summary = "营销标签应用情况监控分析-标签应用数量")
    public Map<String, Object> usageCount(@RequestBody TagUsageCountAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (!isValidDateRange(request.getStartDate(), request.getEndDate())) {
            return buildError(response, "时间范围不合法，格式需为yyyy-MM-dd且开始时间不晚于结束时间");
        }
        return tagApplicationMonitoringService.analyzeUsageCount(request);
    }

    @PostMapping("/usage-frequency")
    @Operation(summary = "营销标签应用情况监控分析-标签应用使用频率")
    public Map<String, Object> usageFrequency(@RequestBody TagUsageFrequencyAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (isBlank(request.getFrequencyType())) {
            return buildError(response, "频率类型不能为空");
        }
        return tagApplicationMonitoringService.analyzeUsageFrequency(request);
    }

    @PostMapping("/impact-scope")
    @Operation(summary = "营销标签应用情况监控分析-标签应用影响范围")
    public Map<String, Object> impactScope(@RequestBody TagImpactScopeAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (isBlank(request.getScopeDimension())) {
            return buildError(response, "影响范围维度不能为空");
        }
        return tagApplicationMonitoringService.analyzeImpactScope(request);
    }

    @PostMapping("/trigger-status")
    @Operation(summary = "实时监控标签应用情况和效果-标签应用触发情况")
    public Map<String, Object> triggerStatus(@RequestBody TagTriggerStatusAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (isBlank(request.getMonitoringWindow())) {
            return buildError(response, "监控窗口不能为空");
        }
        return tagApplicationMonitoringService.monitorTriggerStatus(request);
    }

    @PostMapping("/effectiveness")
    @Operation(summary = "实时监控标签应用情况和效果-标签应用应用效果")
    public Map<String, Object> effectiveness(@RequestBody TagEffectivenessAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (!isBlank(request.getEvaluationDate()) && !isValidDate(request.getEvaluationDate())) {
            return buildError(response, "评估日期格式需为yyyy-MM-dd");
        }
        return tagApplicationMonitoringService.evaluateEffectiveness(request);
    }

    @PostMapping("/user-feedback")
    @Operation(summary = "实时监控标签应用情况和效果-标签应用用户反馈")
    public Map<String, Object> userFeedback(@RequestBody TagUserFeedbackAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getTagName())) {
            return buildError(response, "标签名称不能为空");
        }
        if (isBlank(request.getFeedbackPeriod())) {
            return buildError(response, "反馈周期不能为空");
        }
        return tagApplicationMonitoringService.collectUserFeedback(request);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    private boolean isValidDateRange(String start, String end) {
        if (isBlank(start) && isBlank(end)) {
            return true;
        }
        if (!isValidDate(start) || !isValidDate(end)) {
            return false;
        }
        return start.compareTo(end) <= 0;
    }

    private boolean isValidDate(String date) {
        if (isBlank(date)) {
            return false;
        }
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
