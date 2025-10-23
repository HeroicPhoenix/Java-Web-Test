package com.lvwyh.entity;

public class WarshipWeapon {
    private Long id;
    private String warshipId;
    private String weaponId;
    private String weaponTypeId;
    private Integer weaponNum;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public String getWeaponId() { return weaponId; }
    public void setWeaponId(String weaponId) { this.weaponId = weaponId; }

    public String getWeaponTypeId() { return weaponTypeId; }
    public void setWeaponTypeId(String weaponTypeId) { this.weaponTypeId = weaponTypeId; }

    public Integer getWeaponNum() { return weaponNum; }
    public void setWeaponNum(Integer weaponNum) { this.weaponNum = weaponNum; }
}
