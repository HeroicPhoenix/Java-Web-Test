package com.lvwyh.entity;

import java.math.BigDecimal;

public class WeaponType {
    private Long id;
    private String weaponTypeId;
    private String stateTypeId;
    private String weaponTypeName;
    private Integer level;
    private String parentId;
    private Integer skillPoints;
    private Integer linkNum;
    private Integer cdN;
    private Integer cdX;
    private Integer heDamage;
    private Integer apDamage;
    private Integer sapDamage;
    private Integer actionRadius;
    private Integer speed;
    private Integer penetrationLevelStart;
    private Integer penetrationLevelEnd;
    private BigDecimal coreBreakdown;
    private BigDecimal halfBreakdown;
    private BigDecimal passBreakdown;
    private BigDecimal precision;
    private BigDecimal ignitionRate;
    private BigDecimal leakageRate;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getWeaponTypeId() { return weaponTypeId; }
    public void setWeaponTypeId(String weaponTypeId) { this.weaponTypeId = weaponTypeId; }

    public String getStateTypeId() { return stateTypeId; }
    public void setStateTypeId(String stateTypeId) { this.stateTypeId = stateTypeId; }

    public String getWeaponTypeName() { return weaponTypeName; }
    public void setWeaponTypeName(String weaponTypeName) { this.weaponTypeName = weaponTypeName; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public Integer getSkillPoints() { return skillPoints; }
    public void setSkillPoints(Integer skillPoints) { this.skillPoints = skillPoints; }

    public Integer getLinkNum() { return linkNum; }
    public void setLinkNum(Integer linkNum) { this.linkNum = linkNum; }

    public Integer getCdN() { return cdN; }
    public void setCdN(Integer cdN) { this.cdN = cdN; }

    public Integer getCdX() { return cdX; }
    public void setCdX(Integer cdX) { this.cdX = cdX; }

    public Integer getHeDamage() { return heDamage; }
    public void setHeDamage(Integer heDamage) { this.heDamage = heDamage; }

    public Integer getApDamage() { return apDamage; }
    public void setApDamage(Integer apDamage) { this.apDamage = apDamage; }

    public Integer getSapDamage() { return sapDamage; }
    public void setSapDamage(Integer sapDamage) { this.sapDamage = sapDamage; }

    public Integer getActionRadius() { return actionRadius; }
    public void setActionRadius(Integer actionRadius) { this.actionRadius = actionRadius; }

    public Integer getSpeed() { return speed; }
    public void setSpeed(Integer speed) { this.speed = speed; }

    public Integer getPenetrationLevelStart() { return penetrationLevelStart; }
    public void setPenetrationLevelStart(Integer penetrationLevelStart) { this.penetrationLevelStart = penetrationLevelStart; }

    public Integer getPenetrationLevelEnd() { return penetrationLevelEnd; }
    public void setPenetrationLevelEnd(Integer penetrationLevelEnd) { this.penetrationLevelEnd = penetrationLevelEnd; }

    public BigDecimal getCoreBreakdown() { return coreBreakdown; }
    public void setCoreBreakdown(BigDecimal coreBreakdown) { this.coreBreakdown = coreBreakdown; }

    public BigDecimal getHalfBreakdown() { return halfBreakdown; }
    public void setHalfBreakdown(BigDecimal halfBreakdown) { this.halfBreakdown = halfBreakdown; }

    public BigDecimal getPassBreakdown() { return passBreakdown; }
    public void setPassBreakdown(BigDecimal passBreakdown) { this.passBreakdown = passBreakdown; }

    public BigDecimal getPrecision() { return precision; }
    public void setPrecision(BigDecimal precision) { this.precision = precision; }

    public BigDecimal getIgnitionRate() { return ignitionRate; }
    public void setIgnitionRate(BigDecimal ignitionRate) { this.ignitionRate = ignitionRate; }

    public BigDecimal getLeakageRate() { return leakageRate; }
    public void setLeakageRate(BigDecimal leakageRate) { this.leakageRate = leakageRate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
