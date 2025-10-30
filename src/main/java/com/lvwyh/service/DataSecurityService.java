package com.lvwyh.service;

import com.lvwyh.ao.DataEncryptionRequestAO;
import com.lvwyh.ao.DataMaskingRequestAO;
import com.lvwyh.ao.PermissionControlAO;

import java.util.Map;

public interface DataSecurityService {
    Map<String, Object> applyEncryptionPolicy(DataEncryptionRequestAO request);

    Map<String, Object> configurePermission(PermissionControlAO request);

    Map<String, Object> registerMaskingRule(DataMaskingRequestAO request);
}
