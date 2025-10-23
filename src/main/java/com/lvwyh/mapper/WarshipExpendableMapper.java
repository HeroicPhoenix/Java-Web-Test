package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipExpendable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipExpendableMapper {
    int insert(WarshipExpendable expendable);
    WarshipExpendable select(@Param("warshipId") String warshipId, @Param("expendableTypeId") Long expendableTypeId);
    List<WarshipExpendable> selectByWarshipId(@Param("warshipId") String warshipId);
    int update(WarshipExpendable expendable);
    int delete(@Param("warshipId") String warshipId, @Param("expendableTypeId") Long expendableTypeId);
}
