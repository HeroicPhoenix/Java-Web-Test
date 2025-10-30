package com.lvwyh.entity;

public class DevelopmentStandardRule {
    private Long id;
    private String ruleType;
    private String ruleCode;
    private String ruleName;
    private String requirement;
    private String validatorRegex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getValidatorRegex() {
        return validatorRegex;
    }

    public void setValidatorRegex(String validatorRegex) {
        this.validatorRegex = validatorRegex;
    }
}
