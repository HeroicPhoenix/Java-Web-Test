// RoomRound.java
package com.lvwyh.entity;

import java.time.LocalDateTime;

public class RoomRound {
    private Long id;
    private String roomId;
    private Integer rounds;
    private LocalDateTime startTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public Integer getRounds() { return rounds; }
    public void setRounds(Integer rounds) { this.rounds = rounds; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
}
