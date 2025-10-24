package com.lvwyh.mapper;

import com.lvwyh.entity.WarshipSkill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarshipSkillMapper {
    int insert(WarshipSkill warshipSkill);
    WarshipSkill selectById(@Param("id") Long id);
    List<WarshipSkill> selectByWarshipId(@Param("warshipId") String warshipId);
    int deleteById(@Param("id") Long id);
    int deleteByWarshipId(@Param("warshipId") String warshipId);
}
