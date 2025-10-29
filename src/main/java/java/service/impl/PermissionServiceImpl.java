package java.service.impl;

import java.dto.PermissionRequest;
import java.entity.PermissionRecord;
import java.exception.BusinessException;
import java.mapper.PermissionMapper;
import java.service.PermissionService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper mapper;

    public PermissionServiceImpl(PermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PermissionRecord grantAdminPermission(PermissionRequest request) {
        validate(request);
        if (!"ADMIN".equalsIgnoreCase(request.getRole())) {
            throw new BusinessException("Only ADMIN role can use this interface");
        }
        PermissionRecord record = buildRecord(request, "FULL_CONTROL");
        return mapper.save(record);
    }

    @Override
    public List<PermissionRecord> configureUserPermission(PermissionRequest request) {
        validate(request);
        PermissionRecord record = buildRecord(request, request.getPermission());
        mapper.save(record);
        return mapper.findByRole(request.getRole());
    }

    private PermissionRecord buildRecord(PermissionRequest request, String permissionValue) {
        PermissionRecord record = new PermissionRecord();
        record.setRole(request.getRole());
        record.setResource(request.getResource());
        record.setPermission(permissionValue);
        return record;
    }

    private void validate(PermissionRequest request) {
        if (request == null || isBlank(request.getRole()) || isBlank(request.getResource()) || isBlank(request.getPermission())) {
            throw new BusinessException("Role, resource and permission are required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
