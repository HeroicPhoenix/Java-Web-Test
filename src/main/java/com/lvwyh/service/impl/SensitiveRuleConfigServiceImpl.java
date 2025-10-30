package com.lvwyh.service.impl;

import com.lvwyh.ao.KeyDistributionAO;
import com.lvwyh.ao.KeyGenerationAO;
import com.lvwyh.ao.KeyRotationAO;
import com.lvwyh.ao.KeyStorageAO;
import com.lvwyh.ao.MaskingRuleConfigAO;
import com.lvwyh.entity.EncryptionKey;
import com.lvwyh.entity.KeyDistributionRecord;
import com.lvwyh.entity.KeyRotationRecord;
import com.lvwyh.entity.KeyStorageRecord;
import com.lvwyh.entity.MaskingRuleConfig;
import com.lvwyh.mapper.SensitiveRuleConfigMapper;
import com.lvwyh.service.SensitiveRuleConfigService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensitiveRuleConfigServiceImpl implements SensitiveRuleConfigService {

    private final SensitiveRuleConfigMapper sensitiveRuleConfigMapper;

    public SensitiveRuleConfigServiceImpl(SensitiveRuleConfigMapper sensitiveRuleConfigMapper) {
        this.sensitiveRuleConfigMapper = sensitiveRuleConfigMapper;
    }

    @Override
    public Map<String, Object> configureMaskingRule(MaskingRuleConfigAO request) {
        MaskingRuleConfig config = new MaskingRuleConfig();
        config.setDataType(request.getDataType());
        config.setFieldName(request.getFieldName());
        config.setMaskingStrategy(request.getMaskingStrategy());
        config.setEncryptionAlgorithm(request.getEncryptionAlgorithm());
        config.setPolicyReference(request.getPolicyReference());
        config.setCreatedAt(new Date());
        sensitiveRuleConfigMapper.insertMaskingRule(config);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("ruleId", config.getId());
        return result;
    }

    @Override
    public Map<String, Object> generateKey(KeyGenerationAO request) {
        EncryptionKey key = new EncryptionKey();
        key.setKeyName(request.getKeyName());
        key.setAlgorithm(request.getAlgorithm());
        key.setKeyLength(request.getKeyLength());
        key.setGeneratedAt(new Date());
        sensitiveRuleConfigMapper.insertEncryptionKey(key);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("keyId", key.getId());
        return result;
    }

    @Override
    public Map<String, Object> storeKey(KeyStorageAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        EncryptionKey existing = sensitiveRuleConfigMapper.selectKeyById(request.getKeyId());
        if (existing == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "密钥不存在");
            return result;
        }
        KeyStorageRecord record = new KeyStorageRecord();
        record.setKeyId(request.getKeyId());
        record.setStorageLocation(request.getStorageLocation());
        record.setProtectionLevel(request.getProtectionLevel());
        record.setStoredAt(new Date());
        sensitiveRuleConfigMapper.insertKeyStorage(record);
        result.put("success", Boolean.TRUE);
        result.put("storageId", record.getId());
        return result;
    }

    @Override
    public Map<String, Object> distributeKey(KeyDistributionAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        EncryptionKey existing = sensitiveRuleConfigMapper.selectKeyById(request.getKeyId());
        if (existing == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "密钥不存在");
            return result;
        }
        KeyDistributionRecord record = new KeyDistributionRecord();
        record.setKeyId(request.getKeyId());
        record.setTargetSystem(request.getTargetSystem());
        record.setDistributionMethod(request.getDistributionMethod());
        record.setDistributedAt(new Date());
        sensitiveRuleConfigMapper.insertKeyDistribution(record);
        result.put("success", Boolean.TRUE);
        result.put("distributionId", record.getId());
        return result;
    }

    @Override
    public Map<String, Object> rotateKey(KeyRotationAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        EncryptionKey existing = sensitiveRuleConfigMapper.selectKeyById(request.getKeyId());
        if (existing == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "密钥不存在");
            return result;
        }
        List<KeyRotationRecord> rotationRecords = sensitiveRuleConfigMapper.selectRotationByKey(request.getKeyId());
        KeyRotationRecord record = new KeyRotationRecord();
        record.setKeyId(request.getKeyId());
        record.setRotationPeriodDays(request.getRotationPeriodDays());
        record.setNextRotationDate(parseDate(request.getNextRotationDate()));
        record.setUpdatedAt(new Date());
        sensitiveRuleConfigMapper.insertKeyRotation(record);
        result.put("success", Boolean.TRUE);
        result.put("rotationId", record.getId());
        result.put("previousRotations", rotationRecords);
        return result;
    }

    private Date parseDate(String input) {
        if (input == null) {
            return null;
        }
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (Exception ex) {
            return null;
        }
    }
}
