package com.lvwyh.mapper;

import com.lvwyh.entity.PlayerWarship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerWarshipMapper {
    int insert(PlayerWarship playerWarship);
    PlayerWarship select(@Param("playerId") String playerId, @Param("warshipId") String warshipId);
    List<PlayerWarship> selectByPlayerId(@Param("playerId") String playerId);
    int delete(@Param("playerId") String playerId, @Param("warshipId") String warshipId);
}
