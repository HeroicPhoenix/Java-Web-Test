package java.service.impl;

import java.dto.DataLifecycleRequest;
import java.entity.DataLifecycleRecord;
import java.exception.BusinessException;
import java.mapper.DataLifecycleMapper;
import java.service.DataLifecycleService;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class DataLifecycleServiceImpl implements DataLifecycleService {

    private final DataLifecycleMapper mapper;

    public DataLifecycleServiceImpl(DataLifecycleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DataLifecycleRecord recordLifecycle(DataLifecycleRequest request) {
        validate(request);
        DataLifecycleRecord record = buildRecord(request, "LIFECYCLE_RECORD");
        return mapper.save(record);
    }

    @Override
    public DataLifecycleRecord planShortTerm(DataLifecycleRequest request) {
        validate(request);
        DataLifecycleRecord record = buildRecord(request, "SHORT_TERM_PLAN");
        record.setEndDate(record.getStartDate().plusDays(30));
        return mapper.save(record);
    }

    @Override
    public DataLifecycleRecord planLongTerm(DataLifecycleRequest request) {
        validate(request);
        DataLifecycleRecord record = buildRecord(request, "LONG_TERM_PLAN");
        record.setEndDate(record.getStartDate().plusDays(365));
        return mapper.save(record);
    }

    private DataLifecycleRecord buildRecord(DataLifecycleRequest request, String stage) {
        DataLifecycleRecord record = new DataLifecycleRecord();
        record.setDatasetName(request.getDatasetName());
        record.setLifecycleStage(stage);
        LocalDate start = request.getStartDate() != null ? request.getStartDate() : LocalDate.now();
        record.setStartDate(start);
        record.setEndDate(request.getEndDate());
        return record;
    }

    private void validate(DataLifecycleRequest request) {
        if (request == null || request.getDatasetName() == null || request.getDatasetName().trim().isEmpty()) {
            throw new BusinessException("Dataset name is required");
        }
    }
}
