package com.lvwyh.mapper;

import com.lvwyh.entity.Weapon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WeaponMapper {
    int insert(Weapon weapon);
    Weapon selectById(@Param("id") Long id);
    List<Weapon> selectByRoomAndRounds(@Param("roomId") String roomId, @Param("rounds") Integer rounds);
    int deleteById(@Param("id") Long id);
}
