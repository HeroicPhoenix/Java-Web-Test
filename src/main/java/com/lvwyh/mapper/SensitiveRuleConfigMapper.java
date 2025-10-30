package com.lvwyh.mapper;

import com.lvwyh.entity.EncryptionKey;
import com.lvwyh.entity.KeyDistributionRecord;
import com.lvwyh.entity.KeyRotationRecord;
import com.lvwyh.entity.KeyStorageRecord;
import com.lvwyh.entity.MaskingRuleConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensitiveRuleConfigMapper {
    int insertMaskingRule(MaskingRuleConfig record);

    int insertEncryptionKey(EncryptionKey key);

    int insertKeyStorage(KeyStorageRecord storageRecord);

    int insertKeyDistribution(KeyDistributionRecord distributionRecord);

    int insertKeyRotation(KeyRotationRecord rotationRecord);

    EncryptionKey selectKeyById(@Param("keyId") Long keyId);

    List<KeyRotationRecord> selectRotationByKey(@Param("keyId") Long keyId);
}
