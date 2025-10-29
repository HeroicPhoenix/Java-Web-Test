package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.SlowSqlRequest;
import java.entity.SlowSqlRecord;
import java.service.SlowSqlService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slow-sql")
public class SlowSqlController {

    private final SlowSqlService service;

    public SlowSqlController(SlowSqlService service) {
        this.service = service;
    }

    @Operation(summary = "分类管理查询语句")
    @PostMapping("/categorize")
    public ResponseEntity<CommonResponse<SlowSqlRecord>> categorize(@RequestBody SlowSqlRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("SQL statement and suggestion are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.categorizeQuery(request)));
    }

    @Operation(summary = "数据库结构调整策略")
    @PostMapping("/structure")
    public ResponseEntity<CommonResponse<SlowSqlRecord>> structure(@RequestBody SlowSqlRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("SQL statement and suggestion are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.adjustStructure(request)));
    }

    @Operation(summary = "建立数据库监控记录")
    @PostMapping("/monitoring")
    public ResponseEntity<CommonResponse<SlowSqlRecord>> monitoring(@RequestBody SlowSqlRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("SQL statement and suggestion are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.createMonitoringRecord(request)));
    }

    private boolean invalid(SlowSqlRequest request) {
        return request == null || request.getStatement() == null || request.getStatement().trim().isEmpty()
                || request.getSuggestion() == null || request.getSuggestion().trim().isEmpty();
    }
}
