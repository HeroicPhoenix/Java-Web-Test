package java.service.impl;

import java.dto.RedundantDataRequest;
import java.entity.RedundantDataRecord;
import java.exception.BusinessException;
import java.mapper.RedundantDataMapper;
import java.service.RedundantDataService;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RedundantDataServiceImpl implements RedundantDataService {

    private final RedundantDataMapper mapper;

    public RedundantDataServiceImpl(RedundantDataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public RedundantDataRecord exportRedundantData(RedundantDataRequest request) {
        if (request == null || isBlank(request.getTableName()) || isBlank(request.getReason())) {
            throw new BusinessException("Table name and reason must be provided");
        }
        RedundantDataRecord record = new RedundantDataRecord();
        record.setTableName(request.getTableName());
        record.setDescription(request.getReason());
        record.setHashValue(UUID.randomUUID().toString());
        return mapper.save(record);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
