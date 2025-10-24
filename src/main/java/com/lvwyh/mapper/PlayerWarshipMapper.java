package com.lvwyh.mapper;

import com.lvwyh.entity.PlayerWarship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerWarshipMapper {
    int insert(PlayerWarship playerWarship);
    PlayerWarship selectById(@Param("id") Long id);
    PlayerWarship selectByPlayerAndWarship(@Param("playerId") String playerId, @Param("warshipId") String warshipId);
    List<PlayerWarship> selectByPlayerId(@Param("playerId") String playerId);
    int update(PlayerWarship playerWarship);
    int deleteById(@Param("id") Long id);
    int deleteByPlayerAndWarship(@Param("playerId") String playerId, @Param("warshipId") String warshipId);
}
