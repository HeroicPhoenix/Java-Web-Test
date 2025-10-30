package com.lvwyh.entity;

import java.util.Date;

public class RedundantDataRecord {
    private Long id;
    private String domainName;
    private String tableName;
    private String hashSignature;
    private String issueStatus;
    private String masterDataset;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHashSignature() {
        return hashSignature;
    }

    public void setHashSignature(String hashSignature) {
        this.hashSignature = hashSignature;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getMasterDataset() {
        return masterDataset;
    }

    public void setMasterDataset(String masterDataset) {
        this.masterDataset = masterDataset;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
