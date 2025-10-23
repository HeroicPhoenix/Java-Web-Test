package com.lvwyh.entity;

public class WarshipExpendable {
    private String warshipId;
    private Long expendableTypeId;
    private Integer expendableNum;

    public String getWarshipId() { return warshipId; }
    public void setWarshipId(String warshipId) { this.warshipId = warshipId; }

    public Long getExpendableTypeId() { return expendableTypeId; }
    public void setExpendableTypeId(Long expendableTypeId) { this.expendableTypeId = expendableTypeId; }

    public Integer getExpendableNum() { return expendableNum; }
    public void setExpendableNum(Integer expendableNum) { this.expendableNum = expendableNum; }
}
