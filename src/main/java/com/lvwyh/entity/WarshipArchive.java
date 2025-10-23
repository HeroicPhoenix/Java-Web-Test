package com.lvwyh.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WarshipArchive {
    private Long id;
    private String roomId;
    private String warshipId;
    private String warshipName;
    private String description;
    private LocalDateTime createTime;
    private Integer armorBase;
    private Integer armorAdd;
    private Integer hpBase;
    private Integer hpAdd;
    private Integer hpPlusBase;
    private Integer hpPlusAdd;
    private Integer mobilityBase;
    private Integer mobilityAdd;
    private Integer concealmentBase;
    private Integer concealmentAdd;
    private BigDecimal rate;
    private Integer totalSkillPoints;
    private Integer divingRounds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public String getWarshipName() { return warshipName; }
    public void setWarshipName(String warshipName) { this.warshipName = warshipName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public Integer getArmorBase() { return armorBase; }
    public void setArmorBase(Integer armorBase) { this.armorBase = armorBase; }

    public Integer getArmorAdd() { return armorAdd; }
    public void setArmorAdd(Integer armorAdd) { this.armorAdd = armorAdd; }

    public Integer getHpBase() { return hpBase; }
    public void setHpBase(Integer hpBase) { this.hpBase = hpBase; }

    public Integer getHpAdd() { return hpAdd; }
    public void setHpAdd(Integer hpAdd) { this.hpAdd = hpAdd; }

    public Integer getHpPlusBase() { return hpPlusBase; }
    public void setHpPlusBase(Integer hpPlusBase) { this.hpPlusBase = hpPlusBase; }

    public Integer getHpPlusAdd() { return hpPlusAdd; }
    public void setHpPlusAdd(Integer hpPlusAdd) { this.hpPlusAdd = hpPlusAdd; }

    public Integer getMobilityBase() { return mobilityBase; }
    public void setMobilityBase(Integer mobilityBase) { this.mobilityBase = mobilityBase; }

    public Integer getMobilityAdd() { return mobilityAdd; }
    public void setMobilityAdd(Integer mobilityAdd) { this.mobilityAdd = mobilityAdd; }

    public Integer getConcealmentBase() { return concealmentBase; }
    public void setConcealmentBase(Integer concealmentBase) { this.concealmentBase = concealmentBase; }

    public Integer getConcealmentAdd() { return concealmentAdd; }
    public void setConcealmentAdd(Integer concealmentAdd) { this.concealmentAdd = concealmentAdd; }

    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }

    public Integer getTotalSkillPoints() { return totalSkillPoints; }
    public void setTotalSkillPoints(Integer totalSkillPoints) { this.totalSkillPoints = totalSkillPoints; }

    public Integer getDivingRounds() { return divingRounds; }
    public void setDivingRounds(Integer divingRounds) { this.divingRounds = divingRounds; }
}
