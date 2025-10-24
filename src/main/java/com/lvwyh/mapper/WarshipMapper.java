package com.lvwyh.mapper;

import com.lvwyh.entity.Warship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipMapper {
    int insert(Warship warship);
    Warship selectById(@Param("id") Long id);
    List<Warship> selectByRoomAndRounds(@Param("roomId") String roomId, @Param("rounds") Integer rounds);
    int deleteById(@Param("id") Long id);
}
