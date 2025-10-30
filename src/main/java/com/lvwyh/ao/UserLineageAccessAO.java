package com.lvwyh.ao;

public class UserLineageAccessAO {
    private Long userId;
    private String businessRelation;
    private String accessScope;
    private String approver;

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
}
