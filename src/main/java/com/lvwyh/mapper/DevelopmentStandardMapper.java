package com.lvwyh.mapper;

import com.lvwyh.entity.DevelopmentStandardRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DevelopmentStandardMapper {
    DevelopmentStandardRule selectByRuleCode(@Param("ruleCode") String ruleCode);
}
