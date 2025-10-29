package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.PermissionRequest;
import java.entity.PermissionRecord;
import java.service.PermissionService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @Operation(summary = "权限管理—授权的管理员用户才能进行数据资产清单维护、数据血缘关系等功能管理和修改操作")
    @PostMapping("/admin")
    public ResponseEntity<CommonResponse<PermissionRecord>> admin(@RequestBody PermissionRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Role, resource and permission are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.grantAdminPermission(request)));
    }

    @Operation(summary = "权限管理—普通用户其他可以根据业务对应关系授权是否可查、引用数据血缘相关内容")
    @PostMapping("/user")
    public ResponseEntity<CommonResponse<java.util.List<PermissionRecord>>> user(@RequestBody PermissionRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Role, resource and permission are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureUserPermission(request)));
    }

    private boolean invalid(PermissionRequest request) {
        return request == null || request.getRole() == null || request.getRole().trim().isEmpty()
                || request.getResource() == null || request.getResource().trim().isEmpty()
                || request.getPermission() == null || request.getPermission().trim().isEmpty();
    }
}
