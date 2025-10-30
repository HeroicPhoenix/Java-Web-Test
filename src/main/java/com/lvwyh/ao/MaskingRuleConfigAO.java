package com.lvwyh.ao;

public class MaskingRuleConfigAO {
    private String dataType;
    private String fieldName;
    private String maskingStrategy;
    private String encryptionAlgorithm;
    private String policyReference;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMaskingStrategy() {
        return maskingStrategy;
    }

    public void setMaskingStrategy(String maskingStrategy) {
        this.maskingStrategy = maskingStrategy;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getPolicyReference() {
        return policyReference;
    }

    public void setPolicyReference(String policyReference) {
        this.policyReference = policyReference;
    }
}
