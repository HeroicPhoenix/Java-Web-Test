package com.lvwyh.mapper;

import com.lvwyh.entity.AssetArchiveRecord;
import com.lvwyh.entity.AssetBackupPlan;
import com.lvwyh.entity.AssetDeletionRequest;
import com.lvwyh.entity.AssetStorageChange;
import com.lvwyh.entity.MarketingDataAsset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataAssetOptimizationMapper {
    List<MarketingDataAsset> selectMarketingAssets(@Param("assetType") String assetType,
                                                   @Param("importanceLevel") String importanceLevel,
                                                   @Param("keyword") String keyword,
                                                   @Param("offset") int offset,
                                                   @Param("limit") int limit);

    int countMarketingAssets(@Param("assetType") String assetType,
                             @Param("importanceLevel") String importanceLevel,
                             @Param("keyword") String keyword);

    int updateAssetStorage(@Param("assetId") Long assetId,
                           @Param("storageLevel") String storageLevel,
                           @Param("status") String status);

    int insertStorageChange(AssetStorageChange record);

    int insertBackupPlan(AssetBackupPlan plan);

    int insertArchiveRecord(AssetArchiveRecord record);

    int insertDeletionRequest(AssetDeletionRequest request);
}
