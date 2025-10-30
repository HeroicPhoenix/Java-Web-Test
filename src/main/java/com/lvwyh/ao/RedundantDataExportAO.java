package com.lvwyh.ao;

public class RedundantDataExportAO {
    private String domainName;
    private String outputFormat;
    private Boolean includeResolved;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public Boolean getIncludeResolved() {
        return includeResolved;
    }

    public void setIncludeResolved(Boolean includeResolved) {
        this.includeResolved = includeResolved;
    }
}
