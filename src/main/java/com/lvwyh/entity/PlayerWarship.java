package com.lvwyh.entity;

public class PlayerWarship {
    private Long id;
    private String playerId;
    private String warshipId;
    private Integer stateId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public Integer getStateId() { return stateId; }
    public void setStateId(Integer stateId) { this.stateId = stateId; }
}
