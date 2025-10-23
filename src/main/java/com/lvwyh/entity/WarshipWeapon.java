package com.lvwyh.entity;

public class WarshipWeapon {
    private String warshipId;
    private String weaponId;
    private Long weaponTypeId;
    private Integer weaponNum;

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public String getWeaponId() { return weaponId; }
    public void setWeaponId(String weaponId) { this.weaponId = weaponId; }

    public Long getWeaponTypeId() { return weaponTypeId; }
    public void setWeaponTypeId(Long weaponTypeId) { this.weaponTypeId = weaponTypeId; }

    public Integer getWeaponNum() { return weaponNum; }
    public void setWeaponNum(Integer weaponNum) { this.weaponNum = weaponNum; }
}
