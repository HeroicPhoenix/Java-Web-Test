package com.lvwyh.mapper;

import com.lvwyh.entity.ArmorType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArmorTypeMapper {
    int insert(ArmorType armorType);
    ArmorType selectByLevel(@Param("level") Integer level);
    List<ArmorType> selectAll();
    int update(ArmorType armorType);
    int deleteByLevel(@Param("level") Integer level);
}
