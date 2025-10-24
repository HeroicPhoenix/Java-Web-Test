package com.lvwyh.mapper;

import com.lvwyh.entity.ConcealmentLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConcealmentLevelMapper {
    int insert(ConcealmentLevel concealmentLevel);
    ConcealmentLevel selectByLevel(@Param("level") Integer level);
    List<ConcealmentLevel> selectAll();
    int update(ConcealmentLevel concealmentLevel);
    int deleteById(@Param("id") Long id);
}
