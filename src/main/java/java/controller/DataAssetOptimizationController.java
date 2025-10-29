package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.DataArchivePolicyRequest;
import java.dto.DataAssetCatalogRequest;
import java.dto.DataBackupPolicyRequest;
import java.dto.DataDeletionPolicyRequest;
import java.dto.DataStoragePolicyRequest;
import java.entity.DataAsset;
import java.service.DataAssetOptimizationService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assets")
public class DataAssetOptimizationController {

    private final DataAssetOptimizationService service;

    public DataAssetOptimizationController(DataAssetOptimizationService service) {
        this.service = service;
    }

    @Operation(summary = "营销数据资产目录")
    @PostMapping("/catalog")
    public ResponseEntity<CommonResponse<DataAsset>> createCatalog(@RequestBody DataAssetCatalogRequest request) {
        if (request == null || isBlank(request.getName()) || isBlank(request.getDomain()) || isBlank(request.getOwner())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Name, domain and owner are mandatory"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.createCatalogEntry(request)));
    }

    @Operation(summary = "数据资产优化管理-数据存储")
    @PostMapping("/storage")
    public ResponseEntity<CommonResponse<DataAsset>> configureStorage(@RequestBody DataStoragePolicyRequest request) {
        if (request == null || invalidId(request.getAssetId()) || isBlank(request.getStorageType())
                || request.getRetentionDays() == null || request.getRetentionDays() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Asset id, storage type and retention days are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureStorage(request)));
    }

    @Operation(summary = "数据资产优化管理-数据备份")
    @PostMapping("/backup")
    public ResponseEntity<CommonResponse<DataAsset>> configureBackup(@RequestBody DataBackupPolicyRequest request) {
        if (request == null || invalidId(request.getAssetId()) || isBlank(request.getBackupFrequency())
                || request.getRetentionDays() == null || request.getRetentionDays() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Asset id, backup frequency and positive retention days are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureBackup(request)));
    }

    @Operation(summary = "数据资产优化管理-数据归档")
    @PostMapping("/archive")
    public ResponseEntity<CommonResponse<DataAsset>> configureArchive(@RequestBody DataArchivePolicyRequest request) {
        if (request == null || invalidId(request.getAssetId()) || request.getArchiveAfterDays() == null
                || request.getArchiveAfterDays() <= 0 || isBlank(request.getArchiveLocation())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Asset id, archive days and location are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureArchive(request)));
    }

    @Operation(summary = "数据资产优化管理-数据删除")
    @PostMapping("/deletion")
    public ResponseEntity<CommonResponse<DataAsset>> configureDeletion(@RequestBody DataDeletionPolicyRequest request) {
        if (request == null || invalidId(request.getAssetId()) || isBlank(request.getDeletionStrategy())
                || request.getApprovalRequired() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Asset id, deletion strategy and approval flag are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.configureDeletion(request)));
    }

    @Operation(summary = "查询全部数据资产")
    @GetMapping
    public ResponseEntity<CommonResponse<java.util.List<DataAsset>>> listAll() {
        return ResponseEntity.ok(CommonResponse.success(service.listAll()));
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean invalidId(Long id) {
        return id == null || id <= 0;
    }
}
