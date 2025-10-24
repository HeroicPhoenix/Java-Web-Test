package com.lvwyh.entity;

public class StateType {
    private Long id;
    private Integer stateTypeId;
    private String stateTypeName;
    private String weaponTypes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getStateTypeId() { return stateTypeId; }
    public void setStateTypeId(Integer stateTypeId) { this.stateTypeId = stateTypeId; }

    public String getStateTypeName() { return stateTypeName; }
    public void setStateTypeName(String stateTypeName) { this.stateTypeName = stateTypeName; }

    public String getWeaponTypes() { return weaponTypes; }
    public void setWeaponTypes(String weaponTypes) { this.weaponTypes = weaponTypes; }
}
