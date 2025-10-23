package com.lvwyh.mapper;

import com.lvwyh.entity.WeaponType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WeaponTypeMapper {
    int insert(WeaponType weaponType);
    WeaponType selectById(@Param("id") Long id);
    WeaponType selectByWeaponTypeId(@Param("weaponTypeId") String weaponTypeId);
    List<WeaponType> selectAll();
    int update(WeaponType weaponType);
    int deleteById(@Param("id") Long id);
}
