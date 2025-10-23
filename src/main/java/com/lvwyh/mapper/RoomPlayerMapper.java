package com.lvwyh.mapper;

import com.lvwyh.entity.RoomPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomPlayerMapper {
    int insert(RoomPlayer roomPlayer);
    int batchInsert(@Param("list") List<RoomPlayer> list);
    RoomPlayer selectById(@Param("id") Long id);
    List<RoomPlayer> selectByRoomId(@Param("roomId") String roomId);
    List<Map<String, Object>> selectPlayersInRoom(@Param("roomId") String roomId);
    int update(RoomPlayer roomPlayer);
    int deleteById(@Param("id") Long id);
    int deleteByRoomIdAndPlayerId(@Param("roomId") String roomId, @Param("playerId") String playerId);
    int deleteByRoomId(@Param("roomId") String roomId);
    int deleteByPlayerId(@Param("playerId") String playerId);
}
