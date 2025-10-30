package com.lvwyh.controller;

import com.lvwyh.ao.AdminPermissionGrantAO;
import com.lvwyh.ao.UserLineageAccessAO;
import com.lvwyh.service.DataResponsibilityService;
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
@RequestMapping("/api/data-responsibility")
@Tag(name = "数据责任人管理", description = "数据权限管理接口")
public class DataResponsibilityController {

    private final DataResponsibilityService dataResponsibilityService;

    public DataResponsibilityController(DataResponsibilityService dataResponsibilityService) {
        this.dataResponsibilityService = dataResponsibilityService;
    }

    @PostMapping("/admin-permission")
    @Operation(summary = "权限管理—授权的管理员用户才能进行数据资产清单维护、数据血缘关系等功能管理和修改操作")
    public Map<String, Object> grantAdminPermission(@RequestBody AdminPermissionGrantAO request,
                                                    HttpServletResponse response) {
        if (request == null || request.getAdminUserId() == null || request.getAdminUserId().longValue() <= 0) {
            return buildError(response, "管理员用户ID不合法");
        }
        if (request.getPermissionType() == null || request.getPermissionType().trim().length() == 0) {
            return buildError(response, "权限类型不能为空");
        }
        if (request.getGrantedBy() == null || request.getGrantedBy().trim().length() == 0) {
            return buildError(response, "授权人不能为空");
        }
        return dataResponsibilityService.grantAdminPermission(request);
    }

    @PostMapping("/user-access")
    @Operation(summary = "权限管理—普通用户其他可以根据业务对应关系授权是否可查、引用数据血缘相关内容")
    public Map<String, Object> configureUserAccess(@RequestBody UserLineageAccessAO request,
                                                   HttpServletResponse response) {
        if (request == null || request.getUserId() == null || request.getUserId().longValue() <= 0) {
            return buildError(response, "用户ID不合法");
        }
        if (request.getBusinessRelation() == null || request.getBusinessRelation().trim().length() == 0) {
            return buildError(response, "业务关系不能为空");
        }
        if (request.getAccessScope() == null || request.getAccessScope().trim().length() == 0) {
            return buildError(response, "授权范围不能为空");
        }
        if (request.getApprover() == null || request.getApprover().trim().length() == 0) {
            return buildError(response, "审批人不能为空");
        }
        return dataResponsibilityService.configureUserLineageAccess(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
