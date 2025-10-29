package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.MetadataQueryRequest;
import java.service.MetadataService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

    private final MetadataService service;

    public MetadataController(MetadataService service) {
        this.service = service;
    }

    @Operation(summary = "元数据清单展示")
    @GetMapping("/catalog")
    public ResponseEntity<CommonResponse<java.util.List<java.entity.MetadataRecord>>> display() {
        return ResponseEntity.ok(CommonResponse.success(service.displayCatalog()));
    }

    @Operation(summary = "元数据查询")
    @PostMapping("/query")
    public ResponseEntity<CommonResponse<java.util.List<java.entity.MetadataRecord>>> query(@RequestBody MetadataQueryRequest request) {
        if (request == null || request.getKeyword() == null || request.getKeyword().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Keyword is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.queryMetadata(request)));
    }

    @Operation(summary = "元数据导出")
    @PostMapping("/export")
    public ResponseEntity<CommonResponse<String>> export(@RequestBody MetadataQueryRequest request) {
        if (request == null || request.getKeyword() == null || request.getKeyword().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Keyword is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.exportMetadata(request)));
    }
}
