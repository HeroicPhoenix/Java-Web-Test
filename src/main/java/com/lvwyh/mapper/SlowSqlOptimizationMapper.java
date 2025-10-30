package com.lvwyh.mapper;

import com.lvwyh.entity.DatabaseMonitoringRecord;
import com.lvwyh.entity.DatabaseStructureStrategy;
import com.lvwyh.entity.SlowQueryOptimization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SlowSqlOptimizationMapper {
    int insertSlowQuery(SlowQueryOptimization optimization);

    List<SlowQueryOptimization> selectSlowQueries(@Param("categoryName") String categoryName);

    int insertStructureStrategy(DatabaseStructureStrategy strategy);

    List<DatabaseStructureStrategy> selectStructureStrategies();

    int insertMonitoringRecord(DatabaseMonitoringRecord record);

    List<DatabaseMonitoringRecord> selectMonitoringRecords(@Param("limit") int limit);
}
