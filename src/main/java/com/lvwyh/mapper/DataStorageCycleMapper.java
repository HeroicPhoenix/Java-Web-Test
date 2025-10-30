package com.lvwyh.mapper;

import com.lvwyh.entity.DataLifecycleRecord;
import com.lvwyh.entity.StorageCyclePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataStorageCycleMapper {
    int insertLifecycleRecord(DataLifecycleRecord record);

    List<DataLifecycleRecord> selectLifecycleRecords(@Param("datasetName") String datasetName);

    int insertStoragePlan(StorageCyclePlan plan);

    List<StorageCyclePlan> selectPlansByType(@Param("datasetName") String datasetName,
                                             @Param("strategyType") String strategyType);
}
