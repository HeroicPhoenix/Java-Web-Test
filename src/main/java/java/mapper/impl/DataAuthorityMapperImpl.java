package java.mapper.impl;

import java.entity.DataAuthorityRecord;
import java.mapper.DataAuthorityMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class DataAuthorityMapperImpl implements DataAuthorityMapper {

    private final ConcurrentHashMap<Long, DataAuthorityRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public DataAuthorityRecord save(DataAuthorityRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<DataAuthorityRecord> findByDataset(String datasetName) {
        List<DataAuthorityRecord> result = new ArrayList<>();
        for (DataAuthorityRecord record : storage.values()) {
            if (record.getDatasetName().equalsIgnoreCase(datasetName)) {
                result.add(record);
            }
        }
        return result;
    }
}
