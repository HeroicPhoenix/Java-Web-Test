package com.lvwyh.entity;

import java.util.Date;

public class UserLineageAccess {
    private Long id;
    private Long userId;
    private String businessRelation;
    private String accessScope;
    private String approver;
    private Date authorizedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusinessRelation() {
        return businessRelation;
    }

    public void setBusinessRelation(String businessRelation) {
        this.businessRelation = businessRelation;
    }

    public String getAccessScope() {
        return accessScope;
    }

    public void setAccessScope(String accessScope) {
        this.accessScope = accessScope;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getAuthorizedAt() {
        return authorizedAt;
    }

    public void setAuthorizedAt(Date authorizedAt) {
        this.authorizedAt = authorizedAt;
    }
}
