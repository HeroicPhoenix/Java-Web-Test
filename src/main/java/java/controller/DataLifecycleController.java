package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.DataLifecycleRequest;
import java.entity.DataLifecycleRecord;
import java.service.DataLifecycleService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lifecycle")
public class DataLifecycleController {

    private final DataLifecycleService service;

    public DataLifecycleController(DataLifecycleService service) {
        this.service = service;
    }

    @Operation(summary = "记录数据生命周期")
    @PostMapping("/record")
    public ResponseEntity<CommonResponse<DataLifecycleRecord>> record(@RequestBody DataLifecycleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Dataset name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.recordLifecycle(request)));
    }

    @Operation(summary = "数据存储周期规划-时效性较强的数据，适当缩短存储周期")
    @PostMapping("/plan/short")
    public ResponseEntity<CommonResponse<DataLifecycleRecord>> planShort(@RequestBody DataLifecycleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Dataset name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.planShortTerm(request)));
    }

    @Operation(summary = "数据存储周期规划-长期业务数据，考虑长期存储策略")
    @PostMapping("/plan/long")
    public ResponseEntity<CommonResponse<DataLifecycleRecord>> planLong(@RequestBody DataLifecycleRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Dataset name is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.planLongTerm(request)));
    }

    private boolean invalid(DataLifecycleRequest request) {
        return request == null || request.getDatasetName() == null || request.getDatasetName().trim().isEmpty();
    }
}
