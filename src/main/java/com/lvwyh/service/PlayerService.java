// PlayerService.java
package com.lvwyh.service;

import com.lvwyh.vo.PageResp;
import com.lvwyh.vo.player.PlayerVO;
import java.util.List;

public interface PlayerService {
    PlayerVO create(String playerName);
    PageResp<PlayerVO> page(int page, int size);
    boolean updateName(String playerId, String newName);
    boolean delete(String playerId);

    List<PlayerVO> all();
}
