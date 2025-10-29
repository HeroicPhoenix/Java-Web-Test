package java.service;

import java.dto.QualityRuleRequest;
import java.entity.QualityRule;
import java.util.List;

public interface QualityRuleService {

    QualityRule addRule(QualityRuleRequest request);

    List<QualityRule> listRules();

    void deleteRule(QualityRuleRequest request);
}
