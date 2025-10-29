package java.service.impl;

import java.dto.SlowSqlRequest;
import java.entity.SlowSqlRecord;
import java.exception.BusinessException;
import java.mapper.SlowSqlMapper;
import java.service.SlowSqlService;
import org.springframework.stereotype.Service;

@Service
public class SlowSqlServiceImpl implements SlowSqlService {

    private final SlowSqlMapper mapper;

    public SlowSqlServiceImpl(SlowSqlMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SlowSqlRecord categorizeQuery(SlowSqlRequest request) {
        return buildRecord(request, "QUERY_CATEGORY", "分类管理查询语句");
    }

    @Override
    public SlowSqlRecord adjustStructure(SlowSqlRequest request) {
        return buildRecord(request, "STRUCTURE_ADJUST", "数据库结构调整策略");
    }

    @Override
    public SlowSqlRecord createMonitoringRecord(SlowSqlRequest request) {
        return buildRecord(request, "MONITORING", "建立数据库监控记录");
    }

    private SlowSqlRecord buildRecord(SlowSqlRequest request, String category, String prefix) {
        validate(request);
        SlowSqlRecord record = new SlowSqlRecord();
        record.setCategory(category);
        record.setStatement(request.getStatement());
        record.setOptimizationStrategy(prefix + ": " + request.getSuggestion());
        return mapper.save(record);
    }

    private void validate(SlowSqlRequest request) {
        if (request == null || isBlank(request.getStatement()) || isBlank(request.getSuggestion())) {
            throw new BusinessException("SQL statement and suggestion are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
