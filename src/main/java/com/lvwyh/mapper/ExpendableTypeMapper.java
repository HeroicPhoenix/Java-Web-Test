package com.lvwyh.mapper;

import com.lvwyh.entity.ExpendableType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpendableTypeMapper {
    int insert(ExpendableType expendableType);
    ExpendableType selectById(@Param("id") Long id);
    ExpendableType selectByTypeId(@Param("expendableTypeId") Integer expendableTypeId);
    List<ExpendableType> selectAll();
    int update(ExpendableType expendableType);
    int deleteById(@Param("id") Long id);
}
