package java.service.impl;

import java.dto.QualityRuleRequest;
import java.entity.QualityRule;
import java.exception.BusinessException;
import java.mapper.QualityRuleMapper;
import java.service.QualityRuleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QualityRuleServiceImpl implements QualityRuleService {

    private final QualityRuleMapper mapper;

    public QualityRuleServiceImpl(QualityRuleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public QualityRule addRule(QualityRuleRequest request) {
        validateForAdd(request);
        QualityRule rule = new QualityRule();
        rule.setRuleName(request.getRuleName());
        rule.setExpression(request.getExpression());
        rule.setDescription(request.getDescription());
        return mapper.save(rule);
    }

    @Override
    public List<QualityRule> listRules() {
        return mapper.findAll();
    }

    @Override
    public void deleteRule(QualityRuleRequest request) {
        if (request == null || request.getId() == null || request.getId() <= 0) {
            throw new BusinessException("Rule id is required for deletion");
        }
        mapper.findById(request.getId()).orElseThrow(() -> new BusinessException("Rule not found"));
        mapper.delete(request.getId());
    }

    private void validateForAdd(QualityRuleRequest request) {
        if (request == null || isBlank(request.getRuleName()) || isBlank(request.getExpression())) {
            throw new BusinessException("Rule name and expression are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
