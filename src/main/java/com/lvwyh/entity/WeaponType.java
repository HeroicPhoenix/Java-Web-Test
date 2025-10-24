package com.lvwyh.entity;

public class WeaponType {
    private Long id;
    private Integer weaponTypeId;
    private String weaponTypeName;
    private Integer level;
    private Integer parentId;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getWeaponTypeId() { return weaponTypeId; }
    public void setWeaponTypeId(Integer weaponTypeId) { this.weaponTypeId = weaponTypeId; }

    public String getWeaponTypeName() { return weaponTypeName; }
    public void setWeaponTypeName(String weaponTypeName) { this.weaponTypeName = weaponTypeName; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
