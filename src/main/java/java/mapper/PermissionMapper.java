package java.mapper;

import java.entity.PermissionRecord;
import java.util.List;

public interface PermissionMapper {

    PermissionRecord save(PermissionRecord record);

    List<PermissionRecord> findByRole(String role);
}
