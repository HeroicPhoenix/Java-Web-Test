package java.dto;

public class DataArchivePolicyRequest {

    private Long assetId;
    private Integer archiveAfterDays;
    private String archiveLocation;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Integer getArchiveAfterDays() {
        return archiveAfterDays;
    }

    public void setArchiveAfterDays(Integer archiveAfterDays) {
        this.archiveAfterDays = archiveAfterDays;
    }

    public String getArchiveLocation() {
        return archiveLocation;
    }

    public void setArchiveLocation(String archiveLocation) {
        this.archiveLocation = archiveLocation;
    }
}
