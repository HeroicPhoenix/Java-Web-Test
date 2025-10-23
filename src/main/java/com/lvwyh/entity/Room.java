package com.lvwyh.entity;

public class Room {
    private Long id;
    private String roomId;
    private String roomName;   // ← 新增
    private Integer mapSizeX;
    private Integer mapSizeY;
    private Integer rounds;
    private Boolean isBattle;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getRoomName() { return roomName; }      // ← 新增
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public Integer getMapSizeX() { return mapSizeX; }
    public void setMapSizeX(Integer mapSizeX) { this.mapSizeX = mapSizeX; }

    public Integer getMapSizeY() { return mapSizeY; }
    public void setMapSizeY(Integer mapSizeY) { this.mapSizeY = mapSizeY; }

    public Integer getRounds() { return rounds; }
    public void setRounds(Integer rounds) { this.rounds = rounds; }

    public Boolean getIsBattle() { return isBattle; }
    public void setIsBattle(Boolean isBattle) { this.isBattle = isBattle; }
}
