package com.lvwyh.mapper;

import com.lvwyh.entity.WeaponArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WeaponArchiveMapper {
    int insert(WeaponArchive weaponArchive);
    WeaponArchive selectById(@Param("id") Long id);
    WeaponArchive selectByWeaponModelId(@Param("weaponModelId") Integer weaponModelId);
    List<WeaponArchive> selectByStateTypeId(@Param("stateTypeId") Integer stateTypeId);
    List<WeaponArchive> selectAll();
    int update(WeaponArchive weaponArchive);
    int deleteById(@Param("id") Long id);
}
