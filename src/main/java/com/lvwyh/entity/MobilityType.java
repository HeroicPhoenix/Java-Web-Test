package com.lvwyh.entity;

public class MobilityType {
    private Integer level;
    private Integer distance;
    private Boolean isDiagonal;
    private Boolean isAvoidingTorpedoes;
    private Integer avoidingRoll;
    private Integer avoidingLess;

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getDistance() { return distance; }
    public void setDistance(Integer distance) { this.distance = distance; }

    public Boolean getIsDiagonal() { return isDiagonal; }
    public void setIsDiagonal(Boolean isDiagonal) { this.isDiagonal = isDiagonal; }

    public Boolean getIsAvoidingTorpedoes() { return isAvoidingTorpedoes; }
    public void setIsAvoidingTorpedoes(Boolean isAvoidingTorpedoes) { this.isAvoidingTorpedoes = isAvoidingTorpedoes; }

    public Integer getAvoidingRoll() { return avoidingRoll; }
    public void setAvoidingRoll(Integer avoidingRoll) { this.avoidingRoll = avoidingRoll; }

    public Integer getAvoidingLess() { return avoidingLess; }
    public void setAvoidingLess(Integer avoidingLess) { this.avoidingLess = avoidingLess; }
}
