package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.SecurityRuleRequest;
import java.entity.SecurityRule;
import java.service.SecurityService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final SecurityService service;

    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @Operation(summary = "数据加密")
    @PostMapping("/encrypt")
    public ResponseEntity<CommonResponse<SecurityRule>> encrypt(@RequestBody SecurityRuleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Target is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.encrypt(request)));
    }

    @Operation(summary = "权限控制")
    @PostMapping("/permission")
    public ResponseEntity<CommonResponse<SecurityRule>> permission(@RequestBody SecurityRuleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Target is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.controlPermission(request)));
    }

    @Operation(summary = "数据脱敏")
    @PostMapping("/mask")
    public ResponseEntity<CommonResponse<SecurityRule>> mask(@RequestBody SecurityRuleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Target is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.maskData(request)));
    }

    private boolean invalid(SecurityRuleRequest request) {
        return request == null || request.getTarget() == null || request.getTarget().trim().isEmpty();
    }
}
