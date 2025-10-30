package com.lvwyh.mapper;

import com.lvwyh.entity.AccessControlPolicy;
import com.lvwyh.entity.DataEncryptionPolicy;
import com.lvwyh.entity.DataMaskingRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSecurityMapper {
    DataEncryptionPolicy selectEncryptionPolicy(@Param("datasetName") String datasetName);

    int upsertEncryptionPolicy(DataEncryptionPolicy policy);

    int upsertAccessControlPolicy(AccessControlPolicy policy);

    List<AccessControlPolicy> selectAccessControlPolicies(@Param("roleName") String roleName,
                                                          @Param("datasetName") String datasetName);

    int upsertMaskingRule(DataMaskingRule rule);

    List<DataMaskingRule> selectMaskingRules(@Param("datasetName") String datasetName);
}
