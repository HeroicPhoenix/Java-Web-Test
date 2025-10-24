package com.lvwyh.entity;

public class WarshipExpendable {
    private Long id;
    private String warshipId;
    private Integer expendableTypeId;
    private Integer expendableNum;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public Integer getExpendableTypeId() { return expendableTypeId; }
    public void setExpendableTypeId(Integer expendableTypeId) { this.expendableTypeId = expendableTypeId; }

    public Integer getExpendableNum() { return expendableNum; }
    public void setExpendableNum(Integer expendableNum) { this.expendableNum = expendableNum; }
}
