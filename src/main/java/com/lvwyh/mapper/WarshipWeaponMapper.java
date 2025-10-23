package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipWeapon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipWeaponMapper {
    int insert(WarshipWeapon warshipWeapon);
    WarshipWeapon select(@Param("warshipId") String warshipId, @Param("weaponId") String weaponId);
    List<WarshipWeapon> selectByWarshipId(@Param("warshipId") String warshipId);
    int update(WarshipWeapon warshipWeapon);
    int delete(@Param("warshipId") String warshipId, @Param("weaponId") String weaponId);
}
