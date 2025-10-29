package java.mapper.impl;

import java.entity.RedundantDataRecord;
import java.mapper.RedundantDataMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RedundantDataMapperImpl implements RedundantDataMapper {

    private final ConcurrentHashMap<Long, RedundantDataRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public RedundantDataRecord save(RedundantDataRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<RedundantDataRecord> findAll() {
        return new ArrayList<>(storage.values());
    }
}
