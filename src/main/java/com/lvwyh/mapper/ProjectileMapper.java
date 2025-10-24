package com.lvwyh.mapper;

import com.lvwyh.entity.Projectile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectileMapper {
    int insert(Projectile projectile);
    Projectile selectById(@Param("id") Long id);
    List<Projectile> selectByRoomAndRounds(@Param("roomId") String roomId, @Param("rounds") Integer rounds);
    int deleteById(@Param("id") Long id);
}
