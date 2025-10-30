package com.lvwyh.entity;

import java.util.Date;

public class TagImpactRangeRecord {
    private Long id;
    private String tagName;
    private Integer impactedUsers;
    private String impactedSegments;
    private String scopeDimension;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getImpactedUsers() {
        return impactedUsers;
    }

    public void setImpactedUsers(Integer impactedUsers) {
        this.impactedUsers = impactedUsers;
    }

    public String getImpactedSegments() {
        return impactedSegments;
    }

    public void setImpactedSegments(String impactedSegments) {
        this.impactedSegments = impactedSegments;
    }

    public String getScopeDimension() {
        return scopeDimension;
    }

    public void setScopeDimension(String scopeDimension) {
        this.scopeDimension = scopeDimension;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
