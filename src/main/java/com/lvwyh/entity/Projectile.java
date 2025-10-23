package com.lvwyh.entity;

public class Projectile {
    private Long id;
    private String roomId;
    private String projectileId;
    private String weaponTypeId;
    private Integer rounds;
    private Integer posX;
    private Integer posY;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getProjectileId() { return projectileId; }
    public void setProjectileId(String projectileId) { this.projectileId = projectileId; }

    public String getWeaponTypeId() { return weaponTypeId; }
    public void setWeaponTypeId(String weaponTypeId) { this.weaponTypeId = weaponTypeId; }

    public Integer getRounds() { return rounds; }
    public void setRounds(Integer rounds) { this.rounds = rounds; }

    public Integer getPosX() { return posX; }
    public void setPosX(Integer posX) { this.posX = posX; }

    public Integer getPosY() { return posY; }
    public void setPosY(Integer posY) { this.posY = posY; }
}
