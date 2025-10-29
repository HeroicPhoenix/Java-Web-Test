package java.service.impl;

import java.dto.DataAuthorityRequest;
import java.entity.DataAuthorityRecord;
import java.exception.BusinessException;
import java.mapper.DataAuthorityMapper;
import java.service.DataAuthorityService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DataAuthorityServiceImpl implements DataAuthorityService {

    private final DataAuthorityMapper mapper;

    public DataAuthorityServiceImpl(DataAuthorityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DataAuthorityRecord createAuthority(DataAuthorityRequest request) {
        validate(request);
        DataAuthorityRecord record = buildRecord(request);
        return mapper.save(record);
    }

    @Override
    public List<DataAuthorityRecord> traceSource(DataAuthorityRequest request) {
        validate(request);
        if (request.getDatasetName() == null || request.getDatasetName().trim().isEmpty()) {
            throw new BusinessException("Dataset name is required for tracing");
        }
        if (mapper.findByDataset(request.getDatasetName()).isEmpty()) {
            mapper.save(buildRecord(request));
        }
        return mapper.findByDataset(request.getDatasetName());
    }

    private DataAuthorityRecord buildRecord(DataAuthorityRequest request) {
        DataAuthorityRecord record = new DataAuthorityRecord();
        record.setDatasetName(request.getDatasetName());
        record.setAuthorityName(request.getAuthorityName());
        record.setDescription(request.getDescription());
        record.setContact(request.getContact());
        return record;
    }

    private void validate(DataAuthorityRequest request) {
        if (request == null || isBlank(request.getAuthorityName())) {
            throw new BusinessException("Authority name must be provided");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
