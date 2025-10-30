package com.lvwyh.controller;

import com.lvwyh.ao.BusinessRuleValidationAO;
import com.lvwyh.ao.DataRuleValidationAO;
import com.lvwyh.service.RuleValidationService;
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
@RequestMapping("/api/rule-validation")
@Tag(name = "规则验证", description = "业务规则验证与数据规则验证接口")
public class RuleValidationController {

    @Autowired
    private RuleValidationService ruleValidationService;

    @PostMapping("/business")
    @Operation(summary = "业务规则验证")
    public Map<String, Object> validateBusinessRule(@RequestBody BusinessRuleValidationAO request,
                                                    HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getMetricValue() == null) {
            return buildError(response, "业务规则验证入参不合法");
        }
        return ruleValidationService.validateBusinessRule(request);
    }

    @PostMapping("/data")
    @Operation(summary = "数据规则验证")
    public Map<String, Object> validateDataRule(@RequestBody DataRuleValidationAO request,
                                                HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getFieldValue() == null || request.getFieldValue().trim().length() == 0) {
            return buildError(response, "数据规则验证入参不合法");
        }
        return ruleValidationService.validateDataRule(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
