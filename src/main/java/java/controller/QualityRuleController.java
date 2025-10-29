package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.QualityRuleRequest;
import java.entity.QualityRule;
import java.service.QualityRuleService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rules")
public class QualityRuleController {

    private final QualityRuleService service;

    public QualityRuleController(QualityRuleService service) {
        this.service = service;
    }

    @Operation(summary = "添加校验规则到规则库")
    @PostMapping
    public ResponseEntity<CommonResponse<QualityRule>> addRule(@RequestBody QualityRuleRequest request) {
        if (request == null || request.getRuleName() == null || request.getRuleName().trim().isEmpty()
                || request.getExpression() == null || request.getExpression().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Rule name and expression are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.addRule(request)));
    }

    @Operation(summary = "查询校验规则")
    @GetMapping
    public ResponseEntity<CommonResponse<java.util.List<QualityRule>>> listRules() {
        return ResponseEntity.ok(CommonResponse.success(service.listRules()));
    }

    @Operation(summary = "删除校验规则")
    @PostMapping("/delete")
    public ResponseEntity<CommonResponse<String>> deleteRule(@RequestBody QualityRuleRequest request) {
        if (request == null || request.getId() == null || request.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Valid rule id is required"));
        }
        service.deleteRule(request);
        return ResponseEntity.ok(CommonResponse.success("deleted"));
    }
}
