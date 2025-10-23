package com.lvwyh.mapper;

import com.lvwyh.entity.MobilityType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MobilityTypeMapper {
    int insert(MobilityType mobilityType);
    MobilityType selectByLevel(@Param("level") Integer level);
    List<MobilityType> selectAll();
    int update(MobilityType mobilityType);
    int deleteByLevel(@Param("level") Integer level);
}
