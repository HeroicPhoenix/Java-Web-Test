package com.lvwyh.mapper;

import com.lvwyh.entity.ArmorLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArmorLevelMapper {
    int insert(ArmorLevel armorLevel);
    ArmorLevel selectByLevel(@Param("level") Integer level);
    List<ArmorLevel> selectAll();
    int update(ArmorLevel armorLevel);
    int deleteById(@Param("id") Long id);
}
