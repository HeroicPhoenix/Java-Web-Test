package java.service;

import java.dto.KeyRequest;
import java.entity.KeyManagementRecord;
import java.util.List;

public interface KeyManagementService {

    KeyManagementRecord configureMaskingRule(KeyRequest request);

    KeyManagementRecord generateKey(KeyRequest request);

    KeyManagementRecord storeKey(KeyRequest request);

    KeyManagementRecord distributeKey(KeyRequest request);

    List<KeyManagementRecord> rotateKey(KeyRequest request);
}
