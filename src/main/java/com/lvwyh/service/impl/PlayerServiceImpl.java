// impl/PlayerServiceImpl.java
package com.lvwyh.service.impl;

import com.lvwyh.common.Ids;
import com.lvwyh.entity.Player;
import com.lvwyh.mapper.PlayerMapper;
import com.lvwyh.mapper.RoomPlayerMapper;
import com.lvwyh.service.PlayerService;
import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.player.PlayerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private RoomPlayerMapper roomPlayerMapper;

    @Override
    public PlayerVO create(String playerName) {
        Player p = new Player();
        p.setPlayerId(Ids.shortId(10));  // ← 短ID（10位）
        p.setPlayerName(playerName);
        p.setCreateTime(LocalDateTime.now());
        playerMapper.insert(p);

        PlayerVO vo = new PlayerVO();
        vo.setPlayerId(p.getPlayerId());
        vo.setPlayerName(p.getPlayerName());
        vo.setCreateTime(p.getCreateTime());
        return vo;
    }

    @Override
    public PageResp<PlayerVO> page(int page, int size) {
        int offset = (Math.max(page,1)-1)*Math.max(size,1);
        List<Player> list = playerMapper.selectPage(offset,size);
        long total = playerMapper.count();
        List<PlayerVO> vos = new ArrayList<PlayerVO>();
        for (Player p : list) {
            PlayerVO vo = new PlayerVO();
            vo.setPlayerId(p.getPlayerId());
            vo.setPlayerName(p.getPlayerName());
            vo.setCreateTime(p.getCreateTime());
            vos.add(vo);
        }
        return new PageResp<PlayerVO>(vos, total);
    }
    @Override
    public boolean updateName(String playerId, String newName) {
        return playerMapper.updateNameByPlayerId(playerId, newName) > 0;
    }

    @Override
    public boolean delete(String playerId) {
        // 先删关联，再删玩家（无外键）
        roomPlayerMapper.deleteByPlayerId(playerId);
        return playerMapper.deleteByPlayerId(playerId) > 0;
    }
    @Override
    public List<PlayerVO> all() {
        List<Player> list = playerMapper.selectAll();
        List<PlayerVO> vos = new ArrayList<PlayerVO>();
        for (Player p : list) {
            PlayerVO vo = new PlayerVO();
            vo.setPlayerId(p.getPlayerId());
            vo.setPlayerName(p.getPlayerName());
            vo.setCreateTime(p.getCreateTime());
            vos.add(vo);
        }
        return vos;
    }
}
