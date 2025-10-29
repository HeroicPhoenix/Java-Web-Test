package java.service.impl;

import java.dto.MetricRequest;
import java.entity.MetricRecord;
import java.exception.BusinessException;
import java.mapper.MetricMapper;
import java.service.MetricService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricMapper mapper;

    public MetricServiceImpl(MetricMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MetricRecord recordHistoricalMetric(MetricRequest request) {
        validate(request);
        MetricRecord record = buildRecord(request, "HISTORICAL");
        return mapper.save(record);
    }

    @Override
    public List<MetricRecord> analyzeDailyTrend(MetricRequest request) {
        return simulateTrend(request, 1, "DAY_TREND");
    }

    @Override
    public List<MetricRecord> analyzeWeeklyTrend(MetricRequest request) {
        return simulateTrend(request, 7, "WEEK_TREND");
    }

    @Override
    public List<MetricRecord> analyzeMonthlyTrend(MetricRequest request) {
        return simulateTrend(request, 30, "MONTH_TREND");
    }

    @Override
    public List<MetricRecord> compareYearOverYear(MetricRequest request) {
        return simulateTrend(request, 365, "YOY");
    }

    @Override
    public List<MetricRecord> compareMonthOverMonth(MetricRequest request) {
        return simulateTrend(request, 30, "MOM");
    }

    @Override
    public List<MetricRecord> monitorRealTime(MetricRequest request) {
        validate(request);
        List<MetricRecord> records = mapper.findByMetric(request.getMetricCode());
        if (records.isEmpty()) {
            records = simulateTrend(request, 1, "REALTIME");
        }
        return records;
    }

    private List<MetricRecord> simulateTrend(MetricRequest request, int period, String dimension) {
        validate(request);
        List<MetricRecord> history = mapper.findByMetric(request.getMetricCode());
        if (history.isEmpty()) {
            history.add(mapper.save(buildRecord(request, dimension)));
        }
        LocalDate start = request.getReferenceDate() != null ? request.getReferenceDate() : LocalDate.now();
        return history.stream()
                .map(item -> {
                    MetricRecord clone = new MetricRecord();
                    clone.setMetricCode(item.getMetricCode());
                    clone.setDimension(dimension);
                    clone.setReferenceDate(start.minusDays(period));
                    clone.setValue((request.getValue() != null ? request.getValue() : 100d) * 1.02);
                    clone.setRemark("Simulated " + dimension);
                    return clone;
                }).collect(Collectors.toList());
    }

    private MetricRecord buildRecord(MetricRequest request, String dimension) {
        MetricRecord record = new MetricRecord();
        record.setMetricCode(request.getMetricCode());
        record.setDimension(dimension);
        record.setReferenceDate(request.getReferenceDate() != null ? request.getReferenceDate() : LocalDate.now());
        record.setValue(request.getValue() != null ? request.getValue() : 100d);
        record.setRemark("Recorded " + dimension);
        return record;
    }

    private void validate(MetricRequest request) {
        if (request == null || request.getMetricCode() == null || request.getMetricCode().trim().isEmpty()) {
            throw new BusinessException("Metric code is required");
        }
    }
}
