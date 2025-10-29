package java.service;

import java.dto.PermissionRequest;
import java.entity.PermissionRecord;
import java.util.List;

public interface PermissionService {

    PermissionRecord grantAdminPermission(PermissionRequest request);

    List<PermissionRecord> configureUserPermission(PermissionRequest request);
}
