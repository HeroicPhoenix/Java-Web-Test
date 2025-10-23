// RoomMapper.java
package com.lvwyh.mapper;

import com.lvwyh.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RoomMapper {
    int insert(Room r);
    List<Room> selectPage(@Param("offset") int offset, @Param("limit") int limit);
    long count();
}
