package com.lvwyh.mapper;

import com.lvwyh.entity.AdminPermission;
import com.lvwyh.entity.UserLineageAccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataResponsibilityMapper {
    int insertAdminPermission(AdminPermission permission);

    List<AdminPermission> selectLatestAdminPermissions(@Param("limit") int limit);

    int insertUserLineageAccess(UserLineageAccess access);

    List<UserLineageAccess> selectUserAccessByRelation(@Param("businessRelation") String businessRelation);
}
