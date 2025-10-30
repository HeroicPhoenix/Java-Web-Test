package com.lvwyh.service.impl;

import com.lvwyh.ao.CodingStandardCheckAO;
import com.lvwyh.ao.FieldTypeStandardCheckAO;
import com.lvwyh.ao.IntegrationStandardCheckAO;
import com.lvwyh.ao.LayeringStandardCheckAO;
import com.lvwyh.ao.LineageStandardCheckAO;
import com.lvwyh.ao.SchedulingStandardCheckAO;
import com.lvwyh.ao.TableStandardCheckAO;
import com.lvwyh.entity.DevelopmentStandardRule;
import com.lvwyh.mapper.DevelopmentStandardMapper;
import com.lvwyh.service.DevelopmentStandardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class DevelopmentStandardServiceImpl implements DevelopmentStandardService {

    private final DevelopmentStandardMapper developmentStandardMapper;

    public DevelopmentStandardServiceImpl(DevelopmentStandardMapper developmentStandardMapper) {
        this.developmentStandardMapper = developmentStandardMapper;
    }

    @Override
    public Map<String, Object> checkTableStandard(TableStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getTableDefinition(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                if (input == null) {
                    return false;
                }
                boolean base = input.trim().toUpperCase().startsWith("CREATE TABLE");
                return base && matchesRegexIfPresent(input, rule.getValidatorRegex());
            }
        });
    }

    @Override
    public Map<String, Object> checkFieldTypeStandard(FieldTypeStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getFieldType(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                return matchesRegexIfPresent(input, rule.getValidatorRegex());
            }
        });
    }

    @Override
    public Map<String, Object> checkLineageStandard(LineageStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getLineageDescription(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                if (input == null) {
                    return false;
                }
                boolean containsArrow = input.contains("->");
                return containsArrow && matchesRegexIfPresent(input, rule.getValidatorRegex());
            }
        });
    }

    @Override
    public Map<String, Object> checkCodingStandard(CodingStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getCodeSegment(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                if (input == null) {
                    return false;
                }
                return matchesRegexIfPresent(input, rule.getValidatorRegex());
            }
        });
    }

    @Override
    public Map<String, Object> checkLayeringStandard(LayeringStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getLayerName(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                if (input == null || rule.getRequirement() == null) {
                    return false;
                }
                return rule.getRequirement().equalsIgnoreCase(input);
            }
        });
    }

    @Override
    public Map<String, Object> checkSchedulingStandard(SchedulingStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getScheduleCron(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                return matchesRegexIfPresent(input, rule.getValidatorRegex());
            }
        });
    }

    @Override
    public Map<String, Object> checkIntegrationStandard(IntegrationStandardCheckAO request) {
        DevelopmentStandardRule rule = developmentStandardMapper.selectByRuleCode(request.getRuleCode());
        return evaluateRule(rule, request.getInterfaceName(), new EvaluationDelegate() {
            @Override
            public boolean pass(String input, DevelopmentStandardRule rule) {
                if (input == null || rule.getRequirement() == null) {
                    return false;
                }
                return input.startsWith(rule.getRequirement());
            }
        });
    }

    private Map<String, Object> evaluateRule(DevelopmentStandardRule rule, String value, EvaluationDelegate delegate) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (rule == null) {
            result.put("pass", Boolean.FALSE);
            result.put("message", "Rule not found");
            return result;
        }
        boolean pass = delegate.pass(value, rule);
        result.put("pass", pass);
        result.put("ruleName", rule.getRuleName());
        result.put("requirement", rule.getRequirement());
        return result;
    }

    private boolean matchesRegexIfPresent(String input, String regex) {
        if (regex == null || regex.trim().length() == 0) {
            return input != null;
        }
        if (input == null) {
            return false;
        }
        return Pattern.matches(regex, input);
    }

    private interface EvaluationDelegate {
        boolean pass(String input, DevelopmentStandardRule rule);
    }
}
