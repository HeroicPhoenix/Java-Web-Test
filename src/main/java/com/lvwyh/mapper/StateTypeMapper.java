package com.lvwyh.mapper;

import com.lvwyh.entity.StateType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StateTypeMapper {
    int insert(StateType stateType);
    StateType selectById(@Param("id") Long id);
    StateType selectByStateTypeId(@Param("stateTypeId") Integer stateTypeId);
    List<StateType> selectAll();
    int update(StateType stateType);
    int deleteById(@Param("id") Long id);
}
