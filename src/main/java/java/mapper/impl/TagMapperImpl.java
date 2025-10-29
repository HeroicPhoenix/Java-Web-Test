package java.mapper.impl;

import java.entity.TagUsageRecord;
import java.mapper.TagMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TagMapperImpl implements TagMapper {

    private final ConcurrentHashMap<Long, TagUsageRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public TagUsageRecord save(TagUsageRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<TagUsageRecord> findByTag(String tagCode) {
        List<TagUsageRecord> result = new ArrayList<>();
        for (TagUsageRecord record : storage.values()) {
            if (record.getTagCode().equalsIgnoreCase(tagCode)) {
                result.add(record);
            }
        }
        return result;
    }
}
