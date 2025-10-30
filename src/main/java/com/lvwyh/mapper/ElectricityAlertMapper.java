package com.lvwyh.mapper;

import com.lvwyh.entity.ElectricityUsageRecord;
import com.lvwyh.entity.HighPriceWarningConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ElectricityAlertMapper {
    List<ElectricityUsageRecord> selectUsageByUser(@Param("userId") Long userId,
                                                   @Param("months") int months);

    HighPriceWarningConfig selectWarningConfig();
}
