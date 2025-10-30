package com.lvwyh.mapper;

import com.lvwyh.entity.NamingAdaptationRule;
import com.lvwyh.entity.NamingStandardRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NamingStandardMapper {
    NamingStandardRule selectNamingRule(@Param("ruleCode") String ruleCode);

    NamingAdaptationRule selectAdaptationRule(@Param("ruleCode") String ruleCode);
}
