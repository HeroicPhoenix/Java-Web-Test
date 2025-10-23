// dto/player/PlayerCreateReq.java
package com.lvwyh.dto.player;

import javax.validation.constraints.NotBlank;

public class PlayerCreateReq {
    @NotBlank(message = "玩家名称不能为空")
    private String playerName;

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
