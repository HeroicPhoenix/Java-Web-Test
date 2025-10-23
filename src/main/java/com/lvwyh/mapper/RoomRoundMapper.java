// RoomRoundMapper.java
package com.lvwyh.mapper;

import com.lvwyh.entity.RoomRound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomRoundMapper {
    int insert(RoomRound rr);
}
