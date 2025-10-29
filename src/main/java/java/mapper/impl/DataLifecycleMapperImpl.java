package java.mapper.impl;

import java.entity.DataLifecycleRecord;
import java.mapper.DataLifecycleMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class DataLifecycleMapperImpl implements DataLifecycleMapper {

    private final ConcurrentHashMap<Long, DataLifecycleRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public DataLifecycleRecord save(DataLifecycleRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<DataLifecycleRecord> findByDataset(String datasetName) {
        List<DataLifecycleRecord> result = new ArrayList<>();
        for (DataLifecycleRecord record : storage.values()) {
            if (record.getDatasetName().equalsIgnoreCase(datasetName)) {
                result.add(record);
            }
        }
        return result;
    }
}
