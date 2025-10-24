package com.lvwyh.entity;

import java.time.LocalDateTime;

public class SkillArchive {
    private Long id;
    private String skillId;
    private String skillName;
    private Integer cdN;
    private Integer cdX;
    private Integer useNumber;
    private String description;
    private LocalDateTime createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillId() { return skillId; }
    public void setSkillId(String skillId) { this.skillId = skillId; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public Integer getCdN() { return cdN; }
    public void setCdN(Integer cdN) { this.cdN = cdN; }

    public Integer getCdX() { return cdX; }
    public void setCdX(Integer cdX) { this.cdX = cdX; }

    public Integer getUseNumber() { return useNumber; }
    public void setUseNumber(Integer useNumber) { this.useNumber = useNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
