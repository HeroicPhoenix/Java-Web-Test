package java.service.impl;

import java.dto.SecurityRuleRequest;
import java.entity.SecurityRule;
import java.exception.BusinessException;
import java.mapper.SecurityMapper;
import java.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final SecurityMapper mapper;

    public SecurityServiceImpl(SecurityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SecurityRule encrypt(SecurityRuleRequest request) {
        return buildRule(request, "ENCRYPTION", "AES256");
    }

    @Override
    public SecurityRule controlPermission(SecurityRuleRequest request) {
        return buildRule(request, "PERMISSION", request.getStrategy());
    }

    @Override
    public SecurityRule maskData(SecurityRuleRequest request) {
        return buildRule(request, "MASKING", request.getStrategy());
    }

    private SecurityRule buildRule(SecurityRuleRequest request, String type, String strategyDefault) {
        validate(request);
        SecurityRule rule = new SecurityRule();
        rule.setRuleType(type);
        rule.setTarget(request.getTarget());
        rule.setStrategy(request.getStrategy() != null ? request.getStrategy() : strategyDefault);
        return mapper.save(rule);
    }

    private void validate(SecurityRuleRequest request) {
        if (request == null || request.getTarget() == null || request.getTarget().trim().isEmpty()) {
            throw new BusinessException("Target is required");
        }
    }
}
