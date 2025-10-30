package com.lvwyh.service;

import com.lvwyh.ao.ValidationRuleAddAO;
import com.lvwyh.ao.ValidationRuleDeleteAO;
import com.lvwyh.ao.ValidationRuleQueryAO;

import java.util.Map;

public interface ValidationRuleLibraryService {
    Map<String, Object> addRule(ValidationRuleAddAO request);

    Map<String, Object> queryRules(ValidationRuleQueryAO request);

    Map<String, Object> deleteRule(ValidationRuleDeleteAO request);
}
