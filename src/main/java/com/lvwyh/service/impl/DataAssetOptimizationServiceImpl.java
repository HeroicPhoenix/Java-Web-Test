package com.lvwyh.service.impl;

import com.lvwyh.ao.DataArchiveRequestAO;
import com.lvwyh.ao.DataBackupPlanAO;
import com.lvwyh.ao.DataDeletionRequestAO;
import com.lvwyh.ao.DataStorageOptimizationAO;
import com.lvwyh.ao.MarketingAssetCatalogAO;
import com.lvwyh.entity.AssetArchiveRecord;
import com.lvwyh.entity.AssetBackupPlan;
import com.lvwyh.entity.AssetDeletionRequest;
import com.lvwyh.entity.AssetStorageChange;
import com.lvwyh.entity.MarketingDataAsset;
import com.lvwyh.mapper.DataAssetOptimizationMapper;
import com.lvwyh.service.DataAssetOptimizationService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataAssetOptimizationServiceImpl implements DataAssetOptimizationService {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final DataAssetOptimizationMapper dataAssetOptimizationMapper;

    public DataAssetOptimizationServiceImpl(DataAssetOptimizationMapper dataAssetOptimizationMapper) {
        this.dataAssetOptimizationMapper = dataAssetOptimizationMapper;
    }

    @Override
    public Map<String, Object> getMarketingAssetCatalog(MarketingAssetCatalogAO request) {
        int pageNo = request.getPageNo() == null ? 1 : request.getPageNo();
        int pageSize = request.getPageSize() == null ? 10 : request.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        List<MarketingDataAsset> assets = dataAssetOptimizationMapper.selectMarketingAssets(
                request.getAssetType(), request.getImportanceLevel(), request.getKeyword(), offset, pageSize);
        int total = dataAssetOptimizationMapper.countMarketingAssets(
                request.getAssetType(), request.getImportanceLevel(), request.getKeyword());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("records", assets);
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> optimizeStorage(DataStorageOptimizationAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        int updated = dataAssetOptimizationMapper.updateAssetStorage(request.getAssetId(),
                request.getStorageLevel(), "ACTIVE");
        if (updated > 0) {
            AssetStorageChange change = new AssetStorageChange();
            change.setAssetId(request.getAssetId());
            change.setOperator(request.getOperator());
            change.setStorageLevel(request.getStorageLevel());
            change.setChangeTime(new Date());
            dataAssetOptimizationMapper.insertStorageChange(change);
            result.put("message", "Storage level updated successfully");
            result.put("success", Boolean.TRUE);
        } else {
            result.put("message", "Asset not found or storage unchanged");
            result.put("success", Boolean.FALSE);
        }
        return result;
    }

    @Override
    public Map<String, Object> createBackupPlan(DataBackupPlanAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        AssetBackupPlan plan = new AssetBackupPlan();
        plan.setAssetId(request.getAssetId());
        plan.setBackupStrategy(request.getBackupStrategy());
        plan.setOperator(request.getOperator());
        plan.setStatus("SCHEDULED");
        plan.setScheduleTime(parseDate(request.getScheduleTime()));
        int inserted = dataAssetOptimizationMapper.insertBackupPlan(plan);
        result.put("success", inserted > 0);
        result.put("message", inserted > 0 ? "Backup plan created" : "Failed to create backup plan");
        return result;
    }

    @Override
    public Map<String, Object> archiveAsset(DataArchiveRequestAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        AssetArchiveRecord record = new AssetArchiveRecord();
        record.setAssetId(request.getAssetId());
        record.setArchiveReason(request.getArchiveReason());
        record.setArchiveDate(new Date());
        record.setOperator(request.getOperator());
        int inserted = dataAssetOptimizationMapper.insertArchiveRecord(record);
        result.put("success", inserted > 0);
        result.put("message", inserted > 0 ? "Archive record created" : "Failed to archive asset");
        return result;
    }

    @Override
    public Map<String, Object> deleteAsset(DataDeletionRequestAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        AssetDeletionRequest deletionRequest = new AssetDeletionRequest();
        deletionRequest.setAssetId(request.getAssetId());
        deletionRequest.setReason(request.getReason());
        deletionRequest.setApprovalUser(request.getApprovalUser());
        deletionRequest.setStatus("PENDING");
        deletionRequest.setRequestTime(new Date());
        int inserted = dataAssetOptimizationMapper.insertDeletionRequest(deletionRequest);
        result.put("success", inserted > 0);
        result.put("message", inserted > 0 ? "Deletion request submitted" : "Failed to submit deletion request");
        return result;
    }

    private Date parseDate(String input) {
        if (input == null) {
            return null;
        }
        try {
            return DATE_TIME_FORMAT.parse(input);
        } catch (ParseException ex) {
            return null;
        }
    }
}
