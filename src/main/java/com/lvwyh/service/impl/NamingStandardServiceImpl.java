package com.lvwyh.service.impl;

import com.lvwyh.ao.NodeNamingCheckAO;
import com.lvwyh.ao.ProjectSpaceNamingCheckAO;
import com.lvwyh.ao.TableNamingCheckAO;
import com.lvwyh.entity.NamingStandardRule;
import com.lvwyh.mapper.NamingStandardMapper;
import com.lvwyh.service.NamingStandardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class NamingStandardServiceImpl implements NamingStandardService {

    private final NamingStandardMapper namingStandardMapper;

    public NamingStandardServiceImpl(NamingStandardMapper namingStandardMapper) {
        this.namingStandardMapper = namingStandardMapper;
    }

    @Override
    public Map<String, Object> checkTableNaming(TableNamingCheckAO request) {
        NamingStandardRule rule = namingStandardMapper.selectNamingRule(request.getRuleCode());
        return evaluate(rule, request.getTableName());
    }

    @Override
    public Map<String, Object> checkNodeNaming(NodeNamingCheckAO request) {
        NamingStandardRule rule = namingStandardMapper.selectNamingRule(request.getRuleCode());
        return evaluate(rule, request.getNodeName());
    }

    @Override
    public Map<String, Object> checkProjectSpaceNaming(ProjectSpaceNamingCheckAO request) {
        NamingStandardRule rule = namingStandardMapper.selectNamingRule(request.getRuleCode());
        return evaluate(rule, request.getSpaceName());
    }

    private Map<String, Object> evaluate(NamingStandardRule rule, String value) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (rule == null) {
            result.put("pass", Boolean.FALSE);
            result.put("message", "Rule not found");
            return result;
        }
        boolean pass = value != null && Pattern.matches(rule.getPattern(), value);
        result.put("pass", pass);
        result.put("ruleName", rule.getRuleName());
        result.put("description", rule.getDescription());
        return result;
    }
}
