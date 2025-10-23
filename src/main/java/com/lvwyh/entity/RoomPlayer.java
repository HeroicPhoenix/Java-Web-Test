// RoomPlayer.java
package com.lvwyh.entity;

import java.time.LocalDateTime;

public class RoomPlayer {
    private Long id;
    private String roomId;
    private String playerId;
    private LocalDateTime entryTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
}
