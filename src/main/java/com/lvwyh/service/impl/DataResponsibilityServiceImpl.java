package com.lvwyh.service.impl;

import com.lvwyh.ao.AdminPermissionGrantAO;
import com.lvwyh.ao.UserLineageAccessAO;
import com.lvwyh.entity.AdminPermission;
import com.lvwyh.entity.UserLineageAccess;
import com.lvwyh.mapper.DataResponsibilityMapper;
import com.lvwyh.service.DataResponsibilityService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataResponsibilityServiceImpl implements DataResponsibilityService {

    private final DataResponsibilityMapper dataResponsibilityMapper;

    public DataResponsibilityServiceImpl(DataResponsibilityMapper dataResponsibilityMapper) {
        this.dataResponsibilityMapper = dataResponsibilityMapper;
    }

    @Override
    public Map<String, Object> grantAdminPermission(AdminPermissionGrantAO request) {
        AdminPermission permission = new AdminPermission();
        permission.setAdminUserId(request.getAdminUserId());
        permission.setPermissionType(request.getPermissionType());
        permission.setGrantedBy(request.getGrantedBy());
        permission.setGrantedAt(new Date());
        permission.setExpireAt(parseDateTime(request.getExpireAt()));
        dataResponsibilityMapper.insertAdminPermission(permission);
        List<AdminPermission> permissions = dataResponsibilityMapper.selectLatestAdminPermissions(10);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("permissions", permissions);
        return result;
    }

    @Override
    public Map<String, Object> configureUserLineageAccess(UserLineageAccessAO request) {
        UserLineageAccess access = new UserLineageAccess();
        access.setUserId(request.getUserId());
        access.setBusinessRelation(request.getBusinessRelation());
        access.setAccessScope(request.getAccessScope());
        access.setApprover(request.getApprover());
        access.setAuthorizedAt(new Date());
        dataResponsibilityMapper.insertUserLineageAccess(access);
        List<UserLineageAccess> accesses = dataResponsibilityMapper.selectUserAccessByRelation(request.getBusinessRelation());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("accesses", accesses);
        return result;
    }

    private Date parseDateTime(String value) {
        if (value == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
}
