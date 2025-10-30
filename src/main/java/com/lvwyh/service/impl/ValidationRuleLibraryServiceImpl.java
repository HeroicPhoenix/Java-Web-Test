package com.lvwyh.service.impl;

import com.lvwyh.ao.ValidationRuleAddAO;
import com.lvwyh.ao.ValidationRuleDeleteAO;
import com.lvwyh.ao.ValidationRuleQueryAO;
import com.lvwyh.entity.ValidationRuleEntry;
import com.lvwyh.mapper.ValidationRuleLibraryMapper;
import com.lvwyh.service.ValidationRuleLibraryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationRuleLibraryServiceImpl implements ValidationRuleLibraryService {

    private final ValidationRuleLibraryMapper validationRuleLibraryMapper;

    public ValidationRuleLibraryServiceImpl(ValidationRuleLibraryMapper validationRuleLibraryMapper) {
        this.validationRuleLibraryMapper = validationRuleLibraryMapper;
    }

    @Override
    public Map<String, Object> addRule(ValidationRuleAddAO request) {
        ValidationRuleEntry entry = new ValidationRuleEntry();
        entry.setRuleName(request.getRuleName());
        entry.setRuleCategory(request.getRuleCategory());
        entry.setRuleExpression(request.getRuleExpression());
        entry.setDescription(request.getDescription());
        entry.setCreatedAt(new Date());
        validationRuleLibraryMapper.insertRule(entry);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("ruleId", entry.getId());
        return result;
    }

    @Override
    public Map<String, Object> queryRules(ValidationRuleQueryAO request) {
        List<ValidationRuleEntry> entries = validationRuleLibraryMapper.queryRules(request.getRuleCategory(),
                request.getKeyword());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("rules", entries);
        result.put("count", Integer.valueOf(entries == null ? 0 : entries.size()));
        return result;
    }

    @Override
    public Map<String, Object> deleteRule(ValidationRuleDeleteAO request) {
        int deleted = validationRuleLibraryMapper.deleteRule(request.getRuleId());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.valueOf(deleted > 0));
        result.put("deleted", Integer.valueOf(deleted));
        return result;
    }
}
