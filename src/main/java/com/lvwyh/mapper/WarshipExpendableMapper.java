package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipExpendable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipExpendableMapper {
    int insert(WarshipExpendable warshipExpendable);
    WarshipExpendable selectById(@Param("id") Long id);
    List<WarshipExpendable> selectByWarshipId(@Param("warshipId") String warshipId);
    int update(WarshipExpendable warshipExpendable);
    int deleteById(@Param("id") Long id);
    int deleteByWarshipId(@Param("warshipId") String warshipId);
}
