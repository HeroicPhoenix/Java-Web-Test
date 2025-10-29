package java.dto;

public class DataDeletionPolicyRequest {

    private Long assetId;
    private String deletionStrategy;
    private Boolean approvalRequired;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getDeletionStrategy() {
        return deletionStrategy;
    }

    public void setDeletionStrategy(String deletionStrategy) {
        this.deletionStrategy = deletionStrategy;
    }

    public Boolean getApprovalRequired() {
        return approvalRequired;
    }

    public void setApprovalRequired(Boolean approvalRequired) {
        this.approvalRequired = approvalRequired;
    }
}
