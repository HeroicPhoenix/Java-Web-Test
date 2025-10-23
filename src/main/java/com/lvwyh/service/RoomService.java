// RoomService.java
package com.lvwyh.service;

import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.room.RoomVO;
import java.util.List;
import java.util.Map;

public interface RoomService {
    RoomVO create(String roomName, int mapX, int mapY, List<String> joinPlayerIds);
    List<Map<String, Object>> listPlayers(String roomId);
    boolean addPlayers(String roomId, List<String> playerIds);
    boolean removePlayer(String roomId, String playerId);
    PageResp<RoomVO> page(int page, int size);
}
