package com.lvwyh.service.impl;

import com.lvwyh.ao.DataEncryptionRequestAO;
import com.lvwyh.ao.DataMaskingRequestAO;
import com.lvwyh.ao.PermissionControlAO;
import com.lvwyh.entity.AccessControlPolicy;
import com.lvwyh.entity.DataEncryptionPolicy;
import com.lvwyh.entity.DataMaskingRule;
import com.lvwyh.mapper.DataSecurityMapper;
import com.lvwyh.service.DataSecurityService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSecurityServiceImpl implements DataSecurityService {

    private final DataSecurityMapper dataSecurityMapper;

    public DataSecurityServiceImpl(DataSecurityMapper dataSecurityMapper) {
        this.dataSecurityMapper = dataSecurityMapper;
    }

    @Override
    public Map<String, Object> applyEncryptionPolicy(DataEncryptionRequestAO request) {
        DataEncryptionPolicy existing = dataSecurityMapper.selectEncryptionPolicy(request.getDatasetName());
        DataEncryptionPolicy policy = new DataEncryptionPolicy();
        if (existing != null) {
            policy.setId(existing.getId());
        }
        policy.setDatasetName(request.getDatasetName());
        policy.setAlgorithm(request.getAlgorithm());
        policy.setKeyIdentifier(request.getKeyIdentifier());
        policy.setEnabled(request.getEnabled() != null && request.getEnabled().booleanValue() ? 1 : 0);
        policy.setUpdatedAt(new Date());
        dataSecurityMapper.upsertEncryptionPolicy(policy);
        DataEncryptionPolicy latest = dataSecurityMapper.selectEncryptionPolicy(request.getDatasetName());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("policy", latest);
        return result;
    }

    @Override
    public Map<String, Object> configurePermission(PermissionControlAO request) {
        AccessControlPolicy policy = new AccessControlPolicy();
        policy.setRoleName(request.getRoleName());
        policy.setDatasetName(request.getDatasetName());
        policy.setAccessLevel(request.getAccessLevel());
        policy.setReviewer(request.getReviewer());
        policy.setUpdatedAt(new Date());
        dataSecurityMapper.upsertAccessControlPolicy(policy);
        List<AccessControlPolicy> policies = dataSecurityMapper.selectAccessControlPolicies(request.getRoleName(), request.getDatasetName());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("policies", policies);
        return result;
    }

    @Override
    public Map<String, Object> registerMaskingRule(DataMaskingRequestAO request) {
        DataMaskingRule rule = new DataMaskingRule();
        rule.setDatasetName(request.getDatasetName());
        rule.setFieldName(request.getFieldName());
        rule.setMaskingRule(request.getMaskingRule());
        rule.setSample(request.getSample());
        rule.setUpdatedAt(new Date());
        dataSecurityMapper.upsertMaskingRule(rule);
        List<DataMaskingRule> rules = dataSecurityMapper.selectMaskingRules(request.getDatasetName());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("rules", rules);
        return result;
    }
}
