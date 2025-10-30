package com.lvwyh.service;

import com.lvwyh.ao.BusinessRuleValidationAO;
import com.lvwyh.ao.DataRuleValidationAO;

import java.util.Map;

public interface RuleValidationService {
    Map<String, Object> validateBusinessRule(BusinessRuleValidationAO request);

    Map<String, Object> validateDataRule(DataRuleValidationAO request);
}
