package com.lvwyh.entity;

import java.util.Date;

public class KeyRotationRecord {
    private Long id;
    private Long keyId;
    private Integer rotationPeriodDays;
    private Date nextRotationDate;
    private Date updatedAt;

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

    public Integer getRotationPeriodDays() {
        return rotationPeriodDays;
    }

    public void setRotationPeriodDays(Integer rotationPeriodDays) {
        this.rotationPeriodDays = rotationPeriodDays;
    }

    public Date getNextRotationDate() {
        return nextRotationDate;
    }

    public void setNextRotationDate(Date nextRotationDate) {
        this.nextRotationDate = nextRotationDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
