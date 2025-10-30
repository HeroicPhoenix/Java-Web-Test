package com.lvwyh.entity;

import java.util.Date;

public class AuthoritySourceEntry {
    private Long id;
    private String domainName;
    private String sourceSystem;
    private String description;
    private String owner;
    private Date lastVerifiedAt;

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

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getLastVerifiedAt() {
        return lastVerifiedAt;
    }

    public void setLastVerifiedAt(Date lastVerifiedAt) {
        this.lastVerifiedAt = lastVerifiedAt;
    }
}
