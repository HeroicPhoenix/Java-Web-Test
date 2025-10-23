package com.lvwyh.mapper;

import com.lvwyh.entity.SkillArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkillArchiveMapper {
    int insert(SkillArchive skill);
    SkillArchive selectById(@Param("id") Long id);
    List<SkillArchive> selectAll();
    int update(SkillArchive skill);
    int deleteById(@Param("id") Long id);
}
