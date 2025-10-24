package com.lvwyh.mapper;

import com.lvwyh.entity.MobilityLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MobilityLevelMapper {
    int insert(MobilityLevel mobilityLevel);
    MobilityLevel selectByLevel(@Param("level") Integer level);
    List<MobilityLevel> selectAll();
    int update(MobilityLevel mobilityLevel);
    int deleteById(@Param("id") Long id);
}
