package com.lvwyh.controller;

import com.lvwyh.ao.ValidationRuleAddAO;
import com.lvwyh.ao.ValidationRuleDeleteAO;
import com.lvwyh.ao.ValidationRuleQueryAO;
import com.lvwyh.service.ValidationRuleLibraryService;
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
@RequestMapping("/api/validation-rules")
@Tag(name = "数据校验规则库", description = "数据质量校验规则接口")
public class ValidationRuleLibraryController {

    @Autowired
    private ValidationRuleLibraryService validationRuleLibraryService;

    @PostMapping("/add")
    @Operation(summary = "添加校验规则到规则库")
    public Map<String, Object> addRule(@RequestBody ValidationRuleAddAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getRuleName())) {
            return buildError(response, "规则名称不能为空");
        }
        if (isBlank(request.getRuleCategory())) {
            return buildError(response, "规则分类不能为空");
        }
        if (isBlank(request.getRuleExpression())) {
            return buildError(response, "规则表达式不能为空");
        }
        return validationRuleLibraryService.addRule(request);
    }

    @PostMapping("/query")
    @Operation(summary = "查询校验规则")
    public Map<String, Object> queryRules(@RequestBody ValidationRuleQueryAO request, HttpServletResponse response) {
        if (request == null) {
            return buildError(response, "请求参数不能为空");
        }
        if (isBlank(request.getRuleCategory()) && isBlank(request.getKeyword())) {
            return buildError(response, "规则分类和关键字不能同时为空");
        }
        return validationRuleLibraryService.queryRules(request);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除校验规则")
    public Map<String, Object> deleteRule(@RequestBody ValidationRuleDeleteAO request, HttpServletResponse response) {
        if (request == null || request.getRuleId() == null || request.getRuleId().longValue() <= 0) {
            return buildError(response, "规则ID必须为正数");
        }
        return validationRuleLibraryService.deleteRule(request);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
