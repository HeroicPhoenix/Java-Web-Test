package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipArchiveMapper {
    int insert(WarshipArchive archive);
    WarshipArchive selectById(@Param("id") Long id);
    List<WarshipArchive> selectByRoom(@Param("roomId") String roomId);
    int update(WarshipArchive archive);
    int deleteById(@Param("id") Long id);
}
