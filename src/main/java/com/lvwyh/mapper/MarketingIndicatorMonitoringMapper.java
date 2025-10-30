package com.lvwyh.mapper;

import com.lvwyh.entity.IndicatorComparisonRecord;
import com.lvwyh.entity.IndicatorRealtimeSummary;
import com.lvwyh.entity.IndicatorSnapshot;
import com.lvwyh.entity.IndicatorTrendRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MarketingIndicatorMonitoringMapper {
    List<IndicatorSnapshot> selectIndicatorSnapshots(@Param("indicatorCode") String indicatorCode,
                                                     @Param("startDate") String startDate,
                                                     @Param("endDate") String endDate);

    List<IndicatorTrendRecord> selectTrendRecords(@Param("indicatorCode") String indicatorCode,
                                                  @Param("periodType") String periodType,
                                                  @Param("recentCount") int recentCount);

    IndicatorComparisonRecord selectLatestComparison(@Param("indicatorCode") String indicatorCode,
                                                     @Param("compareType") String compareType,
                                                     @Param("currentPeriod") String currentPeriod);

    List<IndicatorRealtimeSummary> selectRealtimeSummary(@Param("category") String category,
                                                          @Param("includeAlerts") boolean includeAlerts,
                                                          @Param("offset") int offset,
                                                          @Param("limit") int limit);

    int countRealtimeSummary(@Param("category") String category,
                             @Param("includeAlerts") boolean includeAlerts);
}
