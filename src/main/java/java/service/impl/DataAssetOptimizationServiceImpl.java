package java.service.impl;

import java.dto.DataArchivePolicyRequest;
import java.dto.DataAssetCatalogRequest;
import java.dto.DataBackupPolicyRequest;
import java.dto.DataDeletionPolicyRequest;
import java.dto.DataStoragePolicyRequest;
import java.entity.DataAsset;
import java.exception.BusinessException;
import java.mapper.DataAssetOptimizationMapper;
import java.service.DataAssetOptimizationService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DataAssetOptimizationServiceImpl implements DataAssetOptimizationService {

    private final DataAssetOptimizationMapper mapper;

    public DataAssetOptimizationServiceImpl(DataAssetOptimizationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DataAsset createCatalogEntry(DataAssetCatalogRequest request) {
        validateCatalog(request);
        DataAsset asset = new DataAsset();
        asset.setName(request.getName());
        asset.setDomain(request.getDomain());
        asset.setOwner(request.getOwner());
        asset.setDescription(request.getDescription());
        asset.setStorageType("UNSPECIFIED");
        asset.setBackupStrategy("UNSPECIFIED");
        asset.setArchivePolicy("UNSPECIFIED");
        asset.setDeletionPolicy("UNSPECIFIED");
        return mapper.insert(asset);
    }

    @Override
    public DataAsset configureStorage(DataStoragePolicyRequest request) {
        DataAsset asset = getAssetOrThrow(request.getAssetId());
        if (request.getStorageType() == null || request.getStorageType().trim().isEmpty()) {
            throw new BusinessException("Storage type must not be empty");
        }
        if (request.getRetentionDays() == null || request.getRetentionDays() <= 0) {
            throw new BusinessException("Retention days must be positive");
        }
        asset.setStorageType(request.getStorageType().trim());
        asset.setArchivePolicy("Archive after " + request.getRetentionDays() + " days");
        return mapper.update(asset);
    }

    @Override
    public DataAsset configureBackup(DataBackupPolicyRequest request) {
        DataAsset asset = getAssetOrThrow(request.getAssetId());
        if (request.getBackupFrequency() == null || request.getBackupFrequency().trim().isEmpty()) {
            throw new BusinessException("Backup frequency is required");
        }
        if (request.getRetentionDays() == null || request.getRetentionDays() <= 0) {
            throw new BusinessException("Backup retention days must be positive");
        }
        asset.setBackupStrategy(request.getBackupFrequency().trim() + " retention:" + request.getRetentionDays());
        return mapper.update(asset);
    }

    @Override
    public DataAsset configureArchive(DataArchivePolicyRequest request) {
        DataAsset asset = getAssetOrThrow(request.getAssetId());
        if (request.getArchiveAfterDays() == null || request.getArchiveAfterDays() <= 0) {
            throw new BusinessException("Archive days must be positive");
        }
        if (request.getArchiveLocation() == null || request.getArchiveLocation().trim().isEmpty()) {
            throw new BusinessException("Archive location required");
        }
        asset.setArchivePolicy("Archive after " + request.getArchiveAfterDays() + " days to " + request.getArchiveLocation());
        return mapper.update(asset);
    }

    @Override
    public DataAsset configureDeletion(DataDeletionPolicyRequest request) {
        DataAsset asset = getAssetOrThrow(request.getAssetId());
        if (request.getDeletionStrategy() == null || request.getDeletionStrategy().trim().isEmpty()) {
            throw new BusinessException("Deletion strategy required");
        }
        if (request.getApprovalRequired() == null) {
            throw new BusinessException("Approval flag required");
        }
        asset.setDeletionPolicy(request.getDeletionStrategy().trim() + (request.getApprovalRequired() ? " with approval" : " without approval"));
        return mapper.update(asset);
    }

    @Override
    public List<DataAsset> listAll() {
        return mapper.findAll();
    }

    private void validateCatalog(DataAssetCatalogRequest request) {
        if (request == null) {
            throw new BusinessException("Request body cannot be null");
        }
        if (isBlank(request.getName()) || isBlank(request.getDomain()) || isBlank(request.getOwner())) {
            throw new BusinessException("Name, domain and owner are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private DataAsset getAssetOrThrow(Long assetId) {
        if (Objects.isNull(assetId) || assetId <= 0) {
            throw new BusinessException("Asset id must be positive");
        }
        return mapper.findById(assetId).orElseThrow(() -> new BusinessException("Asset not found"));
    }
}
