// impl/RoomServiceImpl.java
package com.lvwyh.service.impl;

import com.lvwyh.common.Ids;
import com.lvwyh.entity.Room;
import com.lvwyh.entity.RoomPlayer;
import com.lvwyh.entity.RoomRound;
import com.lvwyh.mapper.RoomMapper;
import com.lvwyh.mapper.RoomPlayerMapper;
import com.lvwyh.mapper.RoomRoundMapper;
import com.lvwyh.service.RoomService;
import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.room.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomRoundMapper roomRoundMapper;

    @Autowired
    private RoomPlayerMapper roomPlayerMapper;

    @Override
    public RoomVO create(String roomName, int mapX, int mapY, List<String> joinPlayerIds) {
        String rid = Ids.shortId(10);        // ← 短ID（10位）

        Room r = new Room();
        r.setRoomId(rid);
        r.setRoomName(roomName);           // 先给默认值，马上用传参覆盖
        r.setMapSizeX(mapX);
        r.setMapSizeY(mapY);
        r.setRounds(1);
        r.setIsBattle(false);
        // roomName 真正赋值在 Controller / Service 前接收的 req 里，这里只是兜底

        // 这里建议把 roomName 作为参数传进来：见 Controller 处
        roomMapper.insert(r);

        RoomRound rr = new RoomRound();
        rr.setRoomId(rid);
        rr.setRounds(1);
        rr.setStartTime(LocalDateTime.now());
        roomRoundMapper.insert(rr);

        if (joinPlayerIds != null && !joinPlayerIds.isEmpty()) {
            List<RoomPlayer> list = new ArrayList<RoomPlayer>();
            LocalDateTime now = LocalDateTime.now();
            for (String pid : joinPlayerIds) {
                RoomPlayer rp = new RoomPlayer();
                rp.setRoomId(rid);
                rp.setPlayerId(pid);
                rp.setEntryTime(now);
                list.add(rp);
            }
            roomPlayerMapper.batchInsert(list);
        }

        RoomVO vo = new RoomVO();
        vo.setRoomId(rid);
        vo.setRoomName(roomName);
        vo.setMapSizeX(mapX);
        vo.setMapSizeY(mapY);
        vo.setRounds(1);
        vo.setIsBattle(false);
        return vo;
    }

    @Override
    public List<Map<String, Object>> listPlayers(String roomId) {
        return roomPlayerMapper.selectPlayersInRoom(roomId);
    }

    @Override
    public boolean addPlayers(String roomId, List<String> playerIds) {
        if (playerIds == null || playerIds.isEmpty()) return true;
        List<RoomPlayer> rows = new ArrayList<RoomPlayer>();
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        for (String pid : playerIds) {
            RoomPlayer rp = new RoomPlayer();
            rp.setRoomId(roomId);
            rp.setPlayerId(pid);
            rp.setEntryTime(now);
            rows.add(rp);
        }
        return roomPlayerMapper.batchInsert(rows) > 0;
    }

    @Override
    public boolean removePlayer(String roomId, String playerId) {
        return roomPlayerMapper.deleteByRoomIdAndPlayerId(roomId, playerId) > 0;
    }

    @Override
    public PageResp<RoomVO> page(int page, int size) {
        int offset = (Math.max(page,1)-1)*Math.max(size,1);
        List<Room> list = roomMapper.selectPage(offset,size);
        long total = roomMapper.count();
        List<RoomVO> vos = new ArrayList<RoomVO>();
        for (Room r : list) {
            RoomVO vo = new RoomVO();
            vo.setRoomId(r.getRoomId());
            vo.setRoomName(r.getRoomName());
            vo.setMapSizeX(r.getMapSizeX());
            vo.setMapSizeY(r.getMapSizeY());
            vo.setRounds(r.getRounds());
            vo.setIsBattle(r.getIsBattle());
            vos.add(vo);
        }
        return new PageResp<RoomVO>(vos, total);
    }
}
