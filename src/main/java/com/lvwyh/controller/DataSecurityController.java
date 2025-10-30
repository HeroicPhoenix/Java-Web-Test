package com.lvwyh.controller;

import com.lvwyh.ao.DataEncryptionRequestAO;
import com.lvwyh.ao.DataMaskingRequestAO;
import com.lvwyh.ao.PermissionControlAO;
import com.lvwyh.service.DataSecurityService;
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
@RequestMapping("/api/data-security")
@Tag(name = "建立数据主人制应用", description = "数据加密、权限控制与脱敏接口")
public class DataSecurityController {

    @Autowired
    private DataSecurityService dataSecurityService;

    @PostMapping("/encryption")
    @Operation(summary = "数据加密")
    public Map<String, Object> applyEncryption(@RequestBody DataEncryptionRequestAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getAlgorithm() == null || request.getAlgorithm().trim().length() < 3) {
            return buildError(response, "加密算法名称过短");
        }
        if (request.getKeyIdentifier() == null || request.getKeyIdentifier().trim().length() < 4) {
            return buildError(response, "密钥标识至少4位");
        }
        if (request.getEnabled() == null) {
            return buildError(response, "是否启用必须指定");
        }
        return dataSecurityService.applyEncryptionPolicy(request);
    }

    @PostMapping("/permission")
    @Operation(summary = "权限控制")
    public Map<String, Object> configurePermission(@RequestBody PermissionControlAO request,
                                                   HttpServletResponse response) {
        if (request == null || request.getRoleName() == null || request.getRoleName().trim().length() == 0) {
            return buildError(response, "角色名称不能为空");
        }
        if (request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getAccessLevel() == null || request.getAccessLevel().trim().length() == 0) {
            return buildError(response, "访问级别不能为空");
        }
        if (request.getReviewer() == null || request.getReviewer().trim().length() == 0) {
            return buildError(response, "审批人不能为空");
        }
        return dataSecurityService.configurePermission(request);
    }

    @PostMapping("/masking")
    @Operation(summary = "数据脱敏")
    public Map<String, Object> registerMasking(@RequestBody DataMaskingRequestAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().length() == 0) {
            return buildError(response, "数据集名称不能为空");
        }
        if (request.getFieldName() == null || request.getFieldName().trim().length() == 0) {
            return buildError(response, "字段名称不能为空");
        }
        if (request.getMaskingRule() == null || request.getMaskingRule().trim().length() < 3) {
            return buildError(response, "脱敏规则至少3个字符");
        }
        return dataSecurityService.registerMaskingRule(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
