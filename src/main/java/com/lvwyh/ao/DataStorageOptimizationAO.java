package com.lvwyh.ao;

public class DataStorageOptimizationAO {
    private Long assetId;
    private String storageLevel;
    private String operator;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getStorageLevel() {
        return storageLevel;
    }

    public void setStorageLevel(String storageLevel) {
        this.storageLevel = storageLevel;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
