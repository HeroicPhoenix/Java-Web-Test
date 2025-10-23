package com.lvwyh.entity;

public class ExpendableType {
    private Long id;
    private String expendableTypeId;
    private String expendableTypeName;
    private Integer level;
    private String parentId;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getExpendableTypeId() { return expendableTypeId; }
    public void setExpendableTypeId(String expendableTypeId) { this.expendableTypeId = expendableTypeId; }

    public String getExpendableTypeName() { return expendableTypeName; }
    public void setExpendableTypeName(String expendableTypeName) { this.expendableTypeName = expendableTypeName; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
