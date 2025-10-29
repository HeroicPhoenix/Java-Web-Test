package java.service.impl;

import java.dto.KeyRequest;
import java.entity.KeyManagementRecord;
import java.exception.BusinessException;
import java.mapper.KeyManagementMapper;
import java.service.KeyManagementService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class KeyManagementServiceImpl implements KeyManagementService {

    private final KeyManagementMapper mapper;

    public KeyManagementServiceImpl(KeyManagementMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public KeyManagementRecord configureMaskingRule(KeyRequest request) {
        return save(request, "MASK_RULE");
    }

    @Override
    public KeyManagementRecord generateKey(KeyRequest request) {
        return save(request, "KEY_GENERATE");
    }

    @Override
    public KeyManagementRecord storeKey(KeyRequest request) {
        return save(request, "KEY_STORE");
    }

    @Override
    public KeyManagementRecord distributeKey(KeyRequest request) {
        return save(request, "KEY_DISTRIBUTE");
    }

    @Override
    public List<KeyManagementRecord> rotateKey(KeyRequest request) {
        validate(request);
        save(request, "KEY_ROTATE_START");
        return mapper.findByKeyName(request.getKeyName()).stream()
                .map(record -> {
                    KeyManagementRecord clone = new KeyManagementRecord();
                    clone.setAction("KEY_ROTATE_COMPLETE");
                    clone.setKeyName(record.getKeyName());
                    clone.setAlgorithm(record.getAlgorithm());
                    clone.setActionTime(LocalDateTime.now());
                    return mapper.save(clone);
                }).collect(Collectors.toList());
    }

    private KeyManagementRecord save(KeyRequest request, String action) {
        validate(request);
        KeyManagementRecord record = new KeyManagementRecord();
        record.setAction(action);
        record.setKeyName(request.getKeyName());
        record.setAlgorithm(request.getAlgorithm() != null ? request.getAlgorithm() : "AES256");
        record.setActionTime(LocalDateTime.now());
        return mapper.save(record);
    }

    private void validate(KeyRequest request) {
        if (request == null || request.getKeyName() == null || request.getKeyName().trim().isEmpty()) {
            throw new BusinessException("Key name is required");
        }
        if (request.getAlgorithm() == null || request.getAlgorithm().trim().isEmpty()) {
            request.setAlgorithm("AES256");
        }
    }
}
