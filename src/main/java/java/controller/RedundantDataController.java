package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.RedundantDataRequest;
import java.entity.RedundantDataRecord;
import java.service.RedundantDataService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redundant")
public class RedundantDataController {

    private final RedundantDataService service;

    public RedundantDataController(RedundantDataService service) {
        this.service = service;
    }

    @Operation(summary = "导出相关冗余数据清单")
    @PostMapping("/export")
    public ResponseEntity<CommonResponse<RedundantDataRecord>> export(@RequestBody RedundantDataRequest request) {
        if (request == null || request.getTableName() == null || request.getTableName().trim().isEmpty()
                || request.getReason() == null || request.getReason().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Table name and reason are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.exportRedundantData(request)));
    }
}
