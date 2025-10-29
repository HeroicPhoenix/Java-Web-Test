package java.mapper;

import java.entity.MetricRecord;
import java.util.List;

public interface MetricMapper {

    MetricRecord save(MetricRecord record);

    List<MetricRecord> findByMetric(String metricCode);
}
