package com.lvwyh.entity;

public class ConcealmentLevel {
    private Long id;
    private Integer level;
    private Integer discoverRadius;
    private Integer airDiscoverRadius;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getDiscoverRadius() { return discoverRadius; }
    public void setDiscoverRadius(Integer discoverRadius) { this.discoverRadius = discoverRadius; }

    public Integer getAirDiscoverRadius() { return airDiscoverRadius; }
    public void setAirDiscoverRadius(Integer airDiscoverRadius) { this.airDiscoverRadius = airDiscoverRadius; }
}
