package java.mapper.impl;

import java.entity.SlowSqlRecord;
import java.mapper.SlowSqlMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class SlowSqlMapperImpl implements SlowSqlMapper {

    private final ConcurrentHashMap<Long, SlowSqlRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public SlowSqlRecord save(SlowSqlRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<SlowSqlRecord> findByCategory(String category) {
        List<SlowSqlRecord> result = new ArrayList<>();
        for (SlowSqlRecord record : storage.values()) {
            if (record.getCategory().equalsIgnoreCase(category)) {
                result.add(record);
            }
        }
        return result;
    }
}
