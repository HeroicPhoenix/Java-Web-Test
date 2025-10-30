package com.lvwyh.ao;

public class KeyRotationAO {
    private Long keyId;
    private Integer rotationPeriodDays;
    private String nextRotationDate;

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

    public String getNextRotationDate() {
        return nextRotationDate;
    }

    public void setNextRotationDate(String nextRotationDate) {
        this.nextRotationDate = nextRotationDate;
    }
}
