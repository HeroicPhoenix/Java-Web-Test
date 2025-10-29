package java.mapper.impl;

import java.entity.KeyManagementRecord;
import java.mapper.KeyManagementMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class KeyManagementMapperImpl implements KeyManagementMapper {

    private final ConcurrentHashMap<Long, KeyManagementRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public KeyManagementRecord save(KeyManagementRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<KeyManagementRecord> findByKeyName(String keyName) {
        List<KeyManagementRecord> result = new ArrayList<>();
        for (KeyManagementRecord record : storage.values()) {
            if (record.getKeyName().equalsIgnoreCase(keyName)) {
                result.add(record);
            }
        }
        return result;
    }
}
