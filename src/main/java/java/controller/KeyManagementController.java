package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.KeyRequest;
import java.entity.KeyManagementRecord;
import java.service.KeyManagementService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keys")
public class KeyManagementController {

    private final KeyManagementService service;

    public KeyManagementController(KeyManagementService service) {
        this.service = service;
    }

    @Operation(summary = "配置脱敏加密规则")
    @PostMapping("/masking")
    public ResponseEntity<CommonResponse<KeyManagementRecord>> masking(@RequestBody KeyRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Key name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureMaskingRule(request)));
    }

    @Operation(summary = "密钥生成")
    @PostMapping("/generate")
    public ResponseEntity<CommonResponse<KeyManagementRecord>> generate(@RequestBody KeyRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Key name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.generateKey(request)));
    }

    @Operation(summary = "密钥存储")
    @PostMapping("/store")
    public ResponseEntity<CommonResponse<KeyManagementRecord>> store(@RequestBody KeyRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Key name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.storeKey(request)));
    }

    @Operation(summary = "密钥分发")
    @PostMapping("/distribute")
    public ResponseEntity<CommonResponse<KeyManagementRecord>> distribute(@RequestBody KeyRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Key name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.distributeKey(request)));
    }

    @Operation(summary = "密钥轮换")
    @PostMapping("/rotate")
    public ResponseEntity<CommonResponse<java.util.List<KeyManagementRecord>>> rotate(@RequestBody KeyRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Key name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.rotateKey(request)));
    }

    private boolean invalid(KeyRequest request) {
        return request == null || request.getKeyName() == null || request.getKeyName().trim().isEmpty();
    }
}
