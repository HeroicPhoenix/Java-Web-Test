package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.DataAuthorityRequest;
import java.entity.DataAuthorityRecord;
import java.service.DataAuthorityService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authority")
public class DataAuthorityController {

    private final DataAuthorityService service;

    public DataAuthorityController(DataAuthorityService service) {
        this.service = service;
    }

    @Operation(summary = "数据权威源目录清单")
    @PostMapping("/catalog")
    public ResponseEntity<CommonResponse<DataAuthorityRecord>> createCatalog(@RequestBody DataAuthorityRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Authority name and dataset name are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.createAuthority(request)));
    }

    @Operation(summary = "营销指标的数据来源追溯")
    @PostMapping("/trace")
    public ResponseEntity<CommonResponse<java.util.List<DataAuthorityRecord>>> trace(@RequestBody DataAuthorityRequest request) {
        if (invalid(request) || request.getDatasetName() == null || request.getDatasetName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Dataset name and authority are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.traceSource(request)));
    }

    private boolean invalid(DataAuthorityRequest request) {
        return request == null || request.getAuthorityName() == null || request.getAuthorityName().trim().isEmpty()
                || request.getDatasetName() == null || request.getDatasetName().trim().isEmpty();
    }
}
