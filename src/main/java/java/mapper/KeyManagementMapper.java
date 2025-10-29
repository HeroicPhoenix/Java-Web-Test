package java.mapper;

import java.entity.KeyManagementRecord;
import java.util.List;

public interface KeyManagementMapper {

    KeyManagementRecord save(KeyManagementRecord record);

    List<KeyManagementRecord> findByKeyName(String keyName);
}
