// RoomPlayerMapper.java
package com.lvwyh.mapper;

import com.lvwyh.entity.RoomPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoomPlayerMapper {
    int batchInsert(@Param("list") List<RoomPlayer> list);

    // 房间内的玩家明细（用于前端展示成员）
    List<Map<String, Object>> selectPlayersInRoom(@Param("roomId") String roomId);
    // 删除单个关系
    int deleteByRoomIdAndPlayerId(@Param("roomId") String roomId, @Param("playerId") String playerId);
    // 删除某玩家的所有关系（供玩家删除时调用）
    int deleteByPlayerId(@Param("playerId") String playerId);
}
