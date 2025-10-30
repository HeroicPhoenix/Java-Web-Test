package com.lvwyh.service.impl;

import com.lvwyh.ao.NamingAdaptationAO;
import com.lvwyh.entity.NamingAdaptationRule;
import com.lvwyh.mapper.NamingStandardMapper;
import com.lvwyh.service.NamingAdaptationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NamingAdaptationServiceImpl implements NamingAdaptationService {

    private final NamingStandardMapper namingStandardMapper;

    public NamingAdaptationServiceImpl(NamingStandardMapper namingStandardMapper) {
        this.namingStandardMapper = namingStandardMapper;
    }

    @Override
    public Map<String, Object> adaptName(NamingAdaptationAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        NamingAdaptationRule rule = namingStandardMapper.selectAdaptationRule(request.getRuleCode());
        if (rule == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "Rule not found");
            return result;
        }
        StringBuilder builder = new StringBuilder();
        if (rule.getPrefix() != null && rule.getPrefix().length() > 0) {
            builder.append(rule.getPrefix());
        }
        builder.append(request.getOriginalName());
        if (rule.getSuffix() != null && rule.getSuffix().length() > 0) {
            builder.append(rule.getSuffix());
        }
        result.put("success", Boolean.TRUE);
        result.put("adaptedName", builder.toString());
        result.put("ruleName", rule.getRuleName());
        return result;
    }
}
