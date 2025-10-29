package java.mapper.impl;

import java.entity.MetricRecord;
import java.mapper.MetricMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MetricMapperImpl implements MetricMapper {

    private final ConcurrentHashMap<Long, MetricRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public MetricRecord save(MetricRecord record) {
        if (record.getId() == null) {
            record.setId(sequence.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    @Override
    public List<MetricRecord> findByMetric(String metricCode) {
        List<MetricRecord> result = new ArrayList<>();
        for (MetricRecord record : storage.values()) {
            if (record.getMetricCode().equalsIgnoreCase(metricCode)) {
                result.add(record);
            }
        }
        return result;
    }
}
