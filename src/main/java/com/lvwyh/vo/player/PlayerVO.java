// player/PlayerVO.java
package com.lvwyh.vo.player;

import java.time.LocalDateTime;

public class PlayerVO {
    private String playerId;
    private String playerName;
    private LocalDateTime createTime;

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
