package com.lvwyh.entity;

public class WarshipSkill {
    private Long id;
    private String warshipId;
    private String skillId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public String getSkillId() { return skillId; }
    public void setSkillId(String skillId) { this.skillId = skillId; }
}
