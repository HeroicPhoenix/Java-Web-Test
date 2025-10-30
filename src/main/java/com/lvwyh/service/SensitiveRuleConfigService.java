package com.lvwyh.service;

import com.lvwyh.ao.KeyDistributionAO;
import com.lvwyh.ao.KeyGenerationAO;
import com.lvwyh.ao.KeyRotationAO;
import com.lvwyh.ao.KeyStorageAO;
import com.lvwyh.ao.MaskingRuleConfigAO;

import java.util.Map;

public interface SensitiveRuleConfigService {
    Map<String, Object> configureMaskingRule(MaskingRuleConfigAO request);

    Map<String, Object> generateKey(KeyGenerationAO request);

    Map<String, Object> storeKey(KeyStorageAO request);

    Map<String, Object> distributeKey(KeyDistributionAO request);

    Map<String, Object> rotateKey(KeyRotationAO request);
}
