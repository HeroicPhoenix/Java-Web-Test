package com.lvwyh.entity;

public class Warship {
    private Long id;
    private String roomId;
    private String warshipId;
    private Integer rounds;
    private Integer armor;
    private Integer hp;
    private Integer hpPlus;
    private Integer mobility;
    private Integer concealment;
    private Integer rate;
    private Integer posX;
    private Integer posY;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public Integer getRounds() { return rounds; }
    public void setRounds(Integer rounds) { this.rounds = rounds; }

    public Integer getArmor() { return armor; }
    public void setArmor(Integer armor) { this.armor = armor; }

    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }

    public Integer getHpPlus() { return hpPlus; }
    public void setHpPlus(Integer hpPlus) { this.hpPlus = hpPlus; }

    public Integer getMobility() { return mobility; }
    public void setMobility(Integer mobility) { this.mobility = mobility; }

    public Integer getConcealment() { return concealment; }
    public void setConcealment(Integer concealment) { this.concealment = concealment; }

    public Integer getRate() { return rate; }
    public void setRate(Integer rate) { this.rate = rate; }

    public Integer getPosX() { return posX; }
    public void setPosX(Integer posX) { this.posX = posX; }

    public Integer getPosY() { return posY; }
    public void setPosY(Integer posY) { this.posY = posY; }
}
