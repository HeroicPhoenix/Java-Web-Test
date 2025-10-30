package com.lvwyh.mapper;

import com.lvwyh.entity.ValidationRuleEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ValidationRuleLibraryMapper {
    int insertRule(ValidationRuleEntry entry);

    List<ValidationRuleEntry> queryRules(@Param("ruleCategory") String ruleCategory,
                                         @Param("keyword") String keyword);

    int deleteRule(@Param("ruleId") Long ruleId);
}
