package java.mapper.impl;

import java.entity.MetadataRecord;
import java.mapper.MetadataMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MetadataMapperImpl implements MetadataMapper {

    private final ConcurrentHashMap<Long, MetadataRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public MetadataRecord insert(MetadataRecord record) {
        long id = sequence.getAndIncrement();
        record.setId(id);
        storage.put(id, record);
        return record;
    }

    @Override
    public List<MetadataRecord> findAll() {
        return new ArrayList<>(storage.values());
    }
}
