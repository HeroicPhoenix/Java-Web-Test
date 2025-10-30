package com.lvwyh.controller;

import com.lvwyh.ao.KeyDistributionAO;
import com.lvwyh.ao.KeyGenerationAO;
import com.lvwyh.ao.KeyRotationAO;
import com.lvwyh.ao.KeyStorageAO;
import com.lvwyh.ao.MaskingRuleConfigAO;
import com.lvwyh.service.SensitiveRuleConfigService;
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
@RequestMapping("/api/sensitive-rules")
@Tag(name = "敏感数据脱敏加密规则配置维护", description = "脱敏加密规则维护接口")
public class SensitiveRuleConfigController {

    @Autowired
    private SensitiveRuleConfigService sensitiveRuleConfigService;

    @PostMapping("/masking-rule")
    @Operation(summary = "配置脱敏加密规则")
    public Map<String, Object> configureMaskingRule(@RequestBody MaskingRuleConfigAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getDataType())) {
            return buildError(response, "数据类型不能为空");
        }
        if (isBlank(request.getFieldName())) {
            return buildError(response, "字段名称不能为空");
        }
        if (isBlank(request.getMaskingStrategy())) {
            return buildError(response, "脱敏策略不能为空");
        }
        return sensitiveRuleConfigService.configureMaskingRule(request);
    }

    @PostMapping("/generate-key")
    @Operation(summary = "密钥生成")
    public Map<String, Object> generateKey(@RequestBody KeyGenerationAO request, HttpServletResponse response) {
        if (request == null || isBlank(request.getKeyName())) {
            return buildError(response, "密钥名称不能为空");
        }
        if (isBlank(request.getAlgorithm())) {
            return buildError(response, "加密算法不能为空");
        }
        if (request.getKeyLength() == null || request.getKeyLength().intValue() <= 0) {
            return buildError(response, "密钥长度必须为正整数");
        }
        return sensitiveRuleConfigService.generateKey(request);
    }

    @PostMapping("/store-key")
    @Operation(summary = "密钥存储")
    public Map<String, Object> storeKey(@RequestBody KeyStorageAO request, HttpServletResponse response) {
        if (request == null || request.getKeyId() == null || request.getKeyId().longValue() <= 0) {
            return buildError(response, "密钥ID必须为正数");
        }
        if (isBlank(request.getStorageLocation())) {
            return buildError(response, "存储位置不能为空");
        }
        if (isBlank(request.getProtectionLevel())) {
            return buildError(response, "保护级别不能为空");
        }
        return sensitiveRuleConfigService.storeKey(request);
    }

    @PostMapping("/distribute-key")
    @Operation(summary = "密钥分发")
    public Map<String, Object> distributeKey(@RequestBody KeyDistributionAO request, HttpServletResponse response) {
        if (request == null || request.getKeyId() == null || request.getKeyId().longValue() <= 0) {
            return buildError(response, "密钥ID必须为正数");
        }
        if (isBlank(request.getTargetSystem())) {
            return buildError(response, "目标系统不能为空");
        }
        if (isBlank(request.getDistributionMethod())) {
            return buildError(response, "分发方式不能为空");
        }
        return sensitiveRuleConfigService.distributeKey(request);
    }

    @PostMapping("/rotate-key")
    @Operation(summary = "密钥轮换")
    public Map<String, Object> rotateKey(@RequestBody KeyRotationAO request, HttpServletResponse response) {
        if (request == null || request.getKeyId() == null || request.getKeyId().longValue() <= 0) {
            return buildError(response, "密钥ID必须为正数");
        }
        if (request.getRotationPeriodDays() == null || request.getRotationPeriodDays().intValue() <= 0) {
            return buildError(response, "轮换周期必须为正整数");
        }
        if (!isBlank(request.getNextRotationDate()) && !isValidDate(request.getNextRotationDate())) {
            return buildError(response, "下一次轮换日期格式需为yyyy-MM-dd");
        }
        return sensitiveRuleConfigService.rotateKey(request);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    private boolean isValidDate(String value) {
        if (isBlank(value)) {
            return false;
        }
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
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
