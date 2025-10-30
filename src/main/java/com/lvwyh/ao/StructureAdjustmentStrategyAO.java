package com.lvwyh.ao;

public class StructureAdjustmentStrategyAO {
    private String strategyName;
    private String adjustmentDetail;
    private String expectedImpact;
    private String proposer;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getAdjustmentDetail() {
        return adjustmentDetail;
    }

    public void setAdjustmentDetail(String adjustmentDetail) {
        this.adjustmentDetail = adjustmentDetail;
    }

    public String getExpectedImpact() {
        return expectedImpact;
    }

    public void setExpectedImpact(String expectedImpact) {
        this.expectedImpact = expectedImpact;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }
}
