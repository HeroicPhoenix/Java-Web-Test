package com.lvwyh.dto.room;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class RoomCreateReq {
    @NotBlank private String roomName;   // ← 新增
    @NotNull  private Integer mapSizeX;
    @NotNull  private Integer mapSizeY;
    private List<String> joinPlayerIds;

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public Integer getMapSizeX() { return mapSizeX; }
    public void setMapSizeX(Integer mapSizeX) { this.mapSizeX = mapSizeX; }
    public Integer getMapSizeY() { return mapSizeY; }
    public void setMapSizeY(Integer mapSizeY) { this.mapSizeY = mapSizeY; }
    public List<String> getJoinPlayerIds() { return joinPlayerIds; }
    public void setJoinPlayerIds(List<String> joinPlayerIds) { this.joinPlayerIds = joinPlayerIds; }
}
