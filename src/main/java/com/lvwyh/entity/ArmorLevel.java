package com.lvwyh.entity;

public class ArmorLevel {
    private Long id;
    private Integer level;
    private String name;
    private Integer startNum;
    private Integer endNum;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getStartNum() { return startNum; }
    public void setStartNum(Integer startNum) { this.startNum = startNum; }

    public Integer getEndNum() { return endNum; }
    public void setEndNum(Integer endNum) { this.endNum = endNum; }
}
