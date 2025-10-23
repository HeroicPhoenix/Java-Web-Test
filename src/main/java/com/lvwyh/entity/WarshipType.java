package com.lvwyh.entity;

public class WarshipType {
    private Long id;
    private String name;
    private Integer armor;
    private Integer hp;
    private Integer mobility;
    private Integer concealment;
    private Integer actionPriority;
    private String weaponTypes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getArmor() { return armor; }
    public void setArmor(Integer armor) { this.armor = armor; }

    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }

    public Integer getMobility() { return mobility; }
    public void setMobility(Integer mobility) { this.mobility = mobility; }

    public Integer getConcealment() { return concealment; }
    public void setConcealment(Integer concealment) { this.concealment = concealment; }

    public Integer getActionPriority() { return actionPriority; }
    public void setActionPriority(Integer actionPriority) { this.actionPriority = actionPriority; }

    public String getWeaponTypes() { return weaponTypes; }
    public void setWeaponTypes(String weaponTypes) { this.weaponTypes = weaponTypes; }
}
