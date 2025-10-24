package com.lvwyh.mapper;

import com.lvwyh.entity.RoomRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomRoundMapper {
    int insert(RoomRound roomRound);
    RoomRound selectById(@Param("id") Long id);
    List<RoomRound> selectByRoomId(@Param("roomId") String roomId);
    int update(RoomRound roomRound);
    int deleteById(@Param("id") Long id);
    int deleteByRoomId(@Param("roomId") String roomId);
}
