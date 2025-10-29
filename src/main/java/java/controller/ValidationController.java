package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.ValidationRequest;
import java.entity.ValidationRecord;
import java.service.ValidationService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validations")
public class ValidationController {

    private final ValidationService service;

    public ValidationController(ValidationService service) {
        this.service = service;
    }

    @Operation(summary = "业务规则验证")
    @PostMapping("/business-rule")
    public ResponseEntity<CommonResponse<ValidationRecord>> validateBusinessRule(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::validateBusinessRule);
    }

    @Operation(summary = "数据规则验证")
    @PostMapping("/data-rule")
    public ResponseEntity<CommonResponse<ValidationRecord>> validateDataRule(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::validateDataRule);
    }

    @Operation(summary = "建表规范校验")
    @PostMapping("/table-standard")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkTableStandard(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkTableStandard);
    }

    @Operation(summary = "字段类型规范校验")
    @PostMapping("/field-type")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkFieldType(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkFieldType);
    }

    @Operation(summary = "血缘规范校验")
    @PostMapping("/lineage")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkLineage(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkLineageStandard);
    }

    @Operation(summary = "编码规范校验")
    @PostMapping("/coding")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkCoding(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkCodingStandard);
    }

    @Operation(summary = "分层规范校验")
    @PostMapping("/layering")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkLayering(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkLayeringStandard);
    }

    @Operation(summary = "调度规范校验")
    @PostMapping("/scheduling")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkScheduling(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkSchedulingStandard);
    }

    @Operation(summary = "集成规范校验")
    @PostMapping("/integration")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkIntegration(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkIntegrationStandard);
    }

    @Operation(summary = "表模型命名规范校验")
    @PostMapping("/table-naming")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkTableNaming(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkTableNaming);
    }

    @Operation(summary = "节点命名规范校验")
    @PostMapping("/node-naming")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkNodeNaming(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkNodeNaming);
    }

    @Operation(summary = "项目空间命名规范")
    @PostMapping("/project-naming")
    public ResponseEntity<CommonResponse<ValidationRecord>> checkProjectNaming(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::checkProjectNaming);
    }

    @Operation(summary = "自定义命名适配规则")
    @PostMapping("/naming-adapt")
    public ResponseEntity<CommonResponse<ValidationRecord>> adaptNaming(@RequestBody ValidationRequest request) {
        return handleRequest(request, service::adaptNamingRule);
    }

    private ResponseEntity<CommonResponse<ValidationRecord>> handleRequest(ValidationRequest request,
            java.util.function.Function<ValidationRequest, ValidationRecord> function) {
        if (request == null || request.getTargetName() == null || request.getTargetName().trim().isEmpty()
                || request.getRule() == null || request.getRule().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Target name and rule are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(function.apply(request)));
    }
}
