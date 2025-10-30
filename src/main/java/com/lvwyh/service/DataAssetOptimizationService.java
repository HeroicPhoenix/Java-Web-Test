package com.lvwyh.service;

import com.lvwyh.ao.DataArchiveRequestAO;
import com.lvwyh.ao.DataBackupPlanAO;
import com.lvwyh.ao.DataDeletionRequestAO;
import com.lvwyh.ao.DataStorageOptimizationAO;
import com.lvwyh.ao.MarketingAssetCatalogAO;

import java.util.Map;

public interface DataAssetOptimizationService {
    Map<String, Object> getMarketingAssetCatalog(MarketingAssetCatalogAO request);

    Map<String, Object> optimizeStorage(DataStorageOptimizationAO request);

    Map<String, Object> createBackupPlan(DataBackupPlanAO request);

    Map<String, Object> archiveAsset(DataArchiveRequestAO request);

    Map<String, Object> deleteAsset(DataDeletionRequestAO request);
}
