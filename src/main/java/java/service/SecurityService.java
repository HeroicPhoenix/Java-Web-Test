package java.service;

import java.dto.SecurityRuleRequest;
import java.entity.SecurityRule;

public interface SecurityService {

    SecurityRule encrypt(SecurityRuleRequest request);

    SecurityRule controlPermission(SecurityRuleRequest request);

    SecurityRule maskData(SecurityRuleRequest request);
}
