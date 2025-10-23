package com.lvwyh.mapper;

import com.lvwyh.entity.ConcealmentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConcealmentTypeMapper {
    int insert(ConcealmentType concealmentType);
    ConcealmentType selectByLevel(@Param("level") Integer level);
    List<ConcealmentType> selectAll();
    int update(ConcealmentType concealmentType);
    int deleteByLevel(@Param("level") Integer level);
}
