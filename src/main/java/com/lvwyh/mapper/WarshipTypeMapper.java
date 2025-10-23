package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipTypeMapper {
    int insert(WarshipType warshipType);
    WarshipType selectById(@Param("id") Long id);
    List<WarshipType> selectAll();
    int update(WarshipType warshipType);
    int deleteById(@Param("id") Long id);
}
