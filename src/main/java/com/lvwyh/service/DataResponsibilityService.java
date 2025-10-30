package com.lvwyh.service;

import com.lvwyh.ao.AdminPermissionGrantAO;
import com.lvwyh.ao.UserLineageAccessAO;

import java.util.Map;

public interface DataResponsibilityService {
    Map<String, Object> grantAdminPermission(AdminPermissionGrantAO request);

    Map<String, Object> configureUserLineageAccess(UserLineageAccessAO request);
}
