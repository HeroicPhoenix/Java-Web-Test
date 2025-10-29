package java.mapper.impl;

import java.entity.ValidationRecord;
import java.mapper.ValidationMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ValidationMapperImpl implements ValidationMapper {

    private final ConcurrentHashMap<Long, ValidationRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public ValidationRecord save(ValidationRecord record) {
        if (record.getId() == null) {
            long id = sequence.getAndIncrement();
            record.setId(id);
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<ValidationRecord> findByCategory(String category) {
        List<ValidationRecord> result = new ArrayList<>();
        for (ValidationRecord record : storage.values()) {
            if (record.getCategory().equalsIgnoreCase(category)) {
                result.add(record);
            }
        }
        return result;
    }
}
