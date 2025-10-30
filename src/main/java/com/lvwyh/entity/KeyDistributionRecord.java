package com.lvwyh.entity;

import java.util.Date;

public class KeyDistributionRecord {
    private Long id;
    private Long keyId;
    private String targetSystem;
    private String distributionMethod;
    private Date distributedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public Date getDistributedAt() {
        return distributedAt;
    }

    public void setDistributedAt(Date distributedAt) {
        this.distributedAt = distributedAt;
    }
}
