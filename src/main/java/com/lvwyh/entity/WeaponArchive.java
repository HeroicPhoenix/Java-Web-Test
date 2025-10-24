package com.lvwyh.entity;

import java.math.BigDecimal;

public class WeaponArchive {
    private Long id;
    private Integer stateTypeId;
    private Integer weaponTypeId1;
    private Integer weaponTypeId2;
    private Integer weaponModelId;
    private String weaponModelName;
    private Integer skillPoints;
    private Integer cdN;
    private Integer cdX;
    private Integer linkNum;
    private Boolean isDualUse;
    private Integer actionRadius;
    private Integer ammunitionSpeed;
    private String precisionFactor;
    private BigDecimal ignitionRate;
    private BigDecimal leakageRate;
    private Integer damage;
    private Integer heDamage;
    private Integer apDamage;
    private Integer sapDamage;
    private Integer damageRange;
    private Integer penetrationLevelStart;
    private Integer penetrationLevelEnd;
    private BigDecimal coreBreakdown;
    private BigDecimal halfBreakdown;
    private BigDecimal passBreakdown;
    private Integer size;
    private Integer payload;
    private Integer hp;
    private Integer moveSpeed;
    private Integer hangar;
    private Integer recoverySpeedN;
    private Integer recoverySpeedX;
    private Integer arrivalTime;
    private Integer distanceBeforeAttack;
    private String description;
    private Integer weaponTypeId3;
    private Integer weaponTypeId4;
    private Integer weaponTypeId5;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getStateTypeId() { return stateTypeId; }
    public void setStateTypeId(Integer stateTypeId) { this.stateTypeId = stateTypeId; }

    public Integer getWeaponTypeId1() { return weaponTypeId1; }
    public void setWeaponTypeId1(Integer weaponTypeId1) { this.weaponTypeId1 = weaponTypeId1; }

    public Integer getWeaponTypeId2() { return weaponTypeId2; }
    public void setWeaponTypeId2(Integer weaponTypeId2) { this.weaponTypeId2 = weaponTypeId2; }

    public Integer getWeaponModelId() { return weaponModelId; }
    public void setWeaponModelId(Integer weaponModelId) { this.weaponModelId = weaponModelId; }

    public String getWeaponModelName() { return weaponModelName; }
    public void setWeaponModelName(String weaponModelName) { this.weaponModelName = weaponModelName; }

    public Integer getSkillPoints() { return skillPoints; }
    public void setSkillPoints(Integer skillPoints) { this.skillPoints = skillPoints; }

    public Integer getCdN() { return cdN; }
    public void setCdN(Integer cdN) { this.cdN = cdN; }

    public Integer getCdX() { return cdX; }
    public void setCdX(Integer cdX) { this.cdX = cdX; }

    public Integer getLinkNum() { return linkNum; }
    public void setLinkNum(Integer linkNum) { this.linkNum = linkNum; }

    public Boolean getIsDualUse() { return isDualUse; }
    public void setIsDualUse(Boolean isDualUse) { this.isDualUse = isDualUse; }

    public Integer getActionRadius() { return actionRadius; }
    public void setActionRadius(Integer actionRadius) { this.actionRadius = actionRadius; }

    public Integer getAmmunitionSpeed() { return ammunitionSpeed; }
    public void setAmmunitionSpeed(Integer ammunitionSpeed) { this.ammunitionSpeed = ammunitionSpeed; }

    public Boolean getDualUse() {
        return isDualUse;
    }

    public void setDualUse(Boolean dualUse) {
        isDualUse = dualUse;
    }

    public String getPrecisionFactor() {
        return precisionFactor;
    }

    public void setPrecisionFactor(String precisionFactor) {
        this.precisionFactor = precisionFactor;
    }

    public BigDecimal getIgnitionRate() { return ignitionRate; }
    public void setIgnitionRate(BigDecimal ignitionRate) { this.ignitionRate = ignitionRate; }

    public BigDecimal getLeakageRate() { return leakageRate; }
    public void setLeakageRate(BigDecimal leakageRate) { this.leakageRate = leakageRate; }

    public Integer getDamage() { return damage; }
    public void setDamage(Integer damage) { this.damage = damage; }

    public Integer getHeDamage() { return heDamage; }
    public void setHeDamage(Integer heDamage) { this.heDamage = heDamage; }

    public Integer getApDamage() { return apDamage; }
    public void setApDamage(Integer apDamage) { this.apDamage = apDamage; }

    public Integer getSapDamage() { return sapDamage; }
    public void setSapDamage(Integer sapDamage) { this.sapDamage = sapDamage; }

    public Integer getDamageRange() { return damageRange; }
    public void setDamageRange(Integer damageRange) { this.damageRange = damageRange; }

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

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public Integer getPayload() { return payload; }
    public void setPayload(Integer payload) { this.payload = payload; }

    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }

    public Integer getMoveSpeed() { return moveSpeed; }
    public void setMoveSpeed(Integer moveSpeed) { this.moveSpeed = moveSpeed; }

    public Integer getHangar() { return hangar; }
    public void setHangar(Integer hangar) { this.hangar = hangar; }

    public Integer getRecoverySpeedN() { return recoverySpeedN; }
    public void setRecoverySpeedN(Integer recoverySpeedN) { this.recoverySpeedN = recoverySpeedN; }

    public Integer getRecoverySpeedX() { return recoverySpeedX; }
    public void setRecoverySpeedX(Integer recoverySpeedX) { this.recoverySpeedX = recoverySpeedX; }

    public Integer getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Integer arrivalTime) { this.arrivalTime = arrivalTime; }

    public Integer getDistanceBeforeAttack() { return distanceBeforeAttack; }
    public void setDistanceBeforeAttack(Integer distanceBeforeAttack) { this.distanceBeforeAttack = distanceBeforeAttack; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getWeaponTypeId3() { return weaponTypeId3; }
    public void setWeaponTypeId3(Integer weaponTypeId3) { this.weaponTypeId3 = weaponTypeId3; }

    public Integer getWeaponTypeId4() { return weaponTypeId4; }
    public void setWeaponTypeId4(Integer weaponTypeId4) { this.weaponTypeId4 = weaponTypeId4; }

    public Integer getWeaponTypeId5() { return weaponTypeId5; }
    public void setWeaponTypeId5(Integer weaponTypeId5) { this.weaponTypeId5 = weaponTypeId5; }
}
