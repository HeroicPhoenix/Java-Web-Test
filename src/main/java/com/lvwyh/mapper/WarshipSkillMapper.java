package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipSkill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipSkillMapper {
    int insert(WarshipSkill warshipSkill);
    WarshipSkill select(@Param("warshipId") String warshipId, @Param("skillId") Long skillId);
    List<WarshipSkill> selectByWarshipId(@Param("warshipId") String warshipId);
    int delete(@Param("warshipId") String warshipId, @Param("skillId") Long skillId);
}
