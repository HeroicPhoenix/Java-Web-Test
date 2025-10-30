package com.lvwyh.mapper;

import com.lvwyh.entity.BusinessRule;
import com.lvwyh.entity.DataRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RuleValidationMapper {
    BusinessRule selectBusinessRuleByCode(@Param("ruleCode") String ruleCode);

    DataRule selectDataRuleByCode(@Param("ruleCode") String ruleCode);
}
