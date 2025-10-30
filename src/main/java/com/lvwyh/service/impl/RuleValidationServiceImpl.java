package com.lvwyh.service.impl;

import com.lvwyh.ao.BusinessRuleValidationAO;
import com.lvwyh.ao.DataRuleValidationAO;
import com.lvwyh.entity.BusinessRule;
import com.lvwyh.entity.DataRule;
import com.lvwyh.mapper.RuleValidationMapper;
import com.lvwyh.service.RuleValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class RuleValidationServiceImpl implements RuleValidationService {

    private final RuleValidationMapper ruleValidationMapper;

    public RuleValidationServiceImpl(RuleValidationMapper ruleValidationMapper) {
        this.ruleValidationMapper = ruleValidationMapper;
    }

    @Override
    public Map<String, Object> validateBusinessRule(BusinessRuleValidationAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        BusinessRule rule = ruleValidationMapper.selectBusinessRuleByCode(request.getRuleCode());
        if (rule == null) {
            result.put("pass", Boolean.FALSE);
            result.put("message", "Rule not found");
            return result;
        }
        boolean pass = true;
        if ("THRESHOLD".equalsIgnoreCase(rule.getRuleType())) {
            pass = request.getMetricValue() != null && request.getMetricValue() <= rule.getThresholdValue();
        } else if ("MINIMUM".equalsIgnoreCase(rule.getRuleType())) {
            pass = request.getMetricValue() != null && request.getMetricValue() >= rule.getThresholdValue();
        }
        result.put("ruleName", rule.getRuleName());
        result.put("pass", pass);
        result.put("threshold", rule.getThresholdValue());
        return result;
    }

    @Override
    public Map<String, Object> validateDataRule(DataRuleValidationAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        DataRule rule = ruleValidationMapper.selectDataRuleByCode(request.getRuleCode());
        if (rule == null) {
            result.put("pass", Boolean.FALSE);
            result.put("message", "Rule not found");
            return result;
        }
        boolean pass = Pattern.matches(rule.getRegexPattern(), request.getFieldValue());
        result.put("ruleName", rule.getDescription());
        result.put("pass", pass);
        return result;
    }
}
