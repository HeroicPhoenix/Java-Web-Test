package com.lvwyh.mapper;

import com.lvwyh.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {
    int insert(Room room);
    Room selectById(@Param("id") Long id);
    Room selectByRoomId(@Param("roomId") String roomId);
    List<Room> selectPage(@Param("offset") int offset, @Param("limit") int limit);
    long count();
    int update(Room room);
    int deleteById(@Param("id") Long id);
    int deleteByRoomId(@Param("roomId") String roomId);
}
