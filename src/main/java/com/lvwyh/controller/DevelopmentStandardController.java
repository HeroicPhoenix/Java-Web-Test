package com.lvwyh.controller;

import com.lvwyh.ao.CodingStandardCheckAO;
import com.lvwyh.ao.FieldTypeStandardCheckAO;
import com.lvwyh.ao.IntegrationStandardCheckAO;
import com.lvwyh.ao.LayeringStandardCheckAO;
import com.lvwyh.ao.LineageStandardCheckAO;
import com.lvwyh.ao.SchedulingStandardCheckAO;
import com.lvwyh.ao.TableStandardCheckAO;
import com.lvwyh.service.DevelopmentStandardService;
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
@RequestMapping("/api/development-standard")
@Tag(name = "开发规范校验", description = "数据中台开发规范校验接口")
public class DevelopmentStandardController {

    @Autowired
    private DevelopmentStandardService developmentStandardService;


    @PostMapping("/table")
    @Operation(summary = "建表规范校验")
    public Map<String, Object> checkTableStandard(@RequestBody TableStandardCheckAO request,
                                                  HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getTableDefinition() == null || request.getTableDefinition().trim().length() < 20) {
            return buildError(response, "建表规范入参不合法");
        }
        return developmentStandardService.checkTableStandard(request);
    }

    @PostMapping("/field-type")
    @Operation(summary = "字段类型规范校验")
    public Map<String, Object> checkFieldType(@RequestBody FieldTypeStandardCheckAO request,
                                              HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getFieldType() == null || request.getFieldType().trim().length() == 0) {
            return buildError(response, "字段类型校验入参不合法");
        }
        return developmentStandardService.checkFieldTypeStandard(request);
    }

    @PostMapping("/lineage")
    @Operation(summary = "血缘规范校验")
    public Map<String, Object> checkLineage(@RequestBody LineageStandardCheckAO request,
                                            HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getLineageDescription() == null || !request.getLineageDescription().contains("->")) {
            return buildError(response, "血缘描述必须包含流向符号");
        }
        return developmentStandardService.checkLineageStandard(request);
    }

    @PostMapping("/coding")
    @Operation(summary = "编码规范校验")
    public Map<String, Object> checkCoding(@RequestBody CodingStandardCheckAO request,
                                           HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getCodeSegment() == null || request.getCodeSegment().trim().length() < 10) {
            return buildError(response, "编码段落过短");
        }
        return developmentStandardService.checkCodingStandard(request);
    }

    @PostMapping("/layering")
    @Operation(summary = "分层规范校验")
    public Map<String, Object> checkLayering(@RequestBody LayeringStandardCheckAO request,
                                             HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getLayerName() == null || request.getLayerName().trim().length() == 0) {
            return buildError(response, "分层名称不能为空");
        }
        return developmentStandardService.checkLayeringStandard(request);
    }

    @PostMapping("/scheduling")
    @Operation(summary = "调度规范校验")
    public Map<String, Object> checkScheduling(@RequestBody SchedulingStandardCheckAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getScheduleCron() == null || request.getScheduleCron().trim().length() == 0) {
            return buildError(response, "调度表达式不能为空");
        }
        return developmentStandardService.checkSchedulingStandard(request);
    }

    @PostMapping("/integration")
    @Operation(summary = "集成规范校验")
    public Map<String, Object> checkIntegration(@RequestBody IntegrationStandardCheckAO request,
                                                HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getInterfaceName() == null || request.getInterfaceName().trim().length() == 0) {
            return buildError(response, "接口名称不能为空");
        }
        return developmentStandardService.checkIntegrationStandard(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
