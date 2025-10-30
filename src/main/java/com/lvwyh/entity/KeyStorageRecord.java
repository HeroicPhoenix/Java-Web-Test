package com.lvwyh.entity;

import java.util.Date;

public class KeyStorageRecord {
    private Long id;
    private Long keyId;
    private String storageLocation;
    private String protectionLevel;
    private Date storedAt;

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

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getProtectionLevel() {
        return protectionLevel;
    }

    public void setProtectionLevel(String protectionLevel) {
        this.protectionLevel = protectionLevel;
    }

    public Date getStoredAt() {
        return storedAt;
    }

    public void setStoredAt(Date storedAt) {
        this.storedAt = storedAt;
    }
}
