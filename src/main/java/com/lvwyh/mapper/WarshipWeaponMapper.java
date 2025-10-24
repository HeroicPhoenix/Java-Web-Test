package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipWeapon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipWeaponMapper {
    int insert(WarshipWeapon warshipWeapon);
    WarshipWeapon selectById(@Param("id") Long id);
    List<WarshipWeapon> selectByWarshipId(@Param("warshipId") String warshipId);
    int update(WarshipWeapon warshipWeapon);
    int deleteById(@Param("id") Long id);
    int deleteByWarshipId(@Param("warshipId") String warshipId);
}
