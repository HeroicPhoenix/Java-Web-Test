// PlayerMapper.java
package com.lvwyh.mapper;

import com.lvwyh.entity.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PlayerMapper {
    int insert(Player p);
    List<Player> selectPage(@Param("offset") int offset, @Param("limit") int limit);
    int updateNameByPlayerId(@Param("playerId") String playerId, @Param("playerName") String playerName);
    int deleteByPlayerId(@Param("playerId") String playerId);

    long count();
    List<Player> selectAll();
}
