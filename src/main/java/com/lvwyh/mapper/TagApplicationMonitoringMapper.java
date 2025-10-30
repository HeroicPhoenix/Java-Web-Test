package com.lvwyh.mapper;

import com.lvwyh.entity.TagEffectivenessRecord;
import com.lvwyh.entity.TagFeedbackRecord;
import com.lvwyh.entity.TagImpactRangeRecord;
import com.lvwyh.entity.TagTriggerLog;
import com.lvwyh.entity.TagUsageFrequencyRecord;
import com.lvwyh.entity.TagUsageSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagApplicationMonitoringMapper {
    TagUsageSummary selectUsageSummary(@Param("tagName") String tagName,
                                       @Param("startDate") String startDate,
                                       @Param("endDate") String endDate);

    TagUsageFrequencyRecord selectFrequencyRecord(@Param("tagName") String tagName,
                                                  @Param("frequencyType") String frequencyType,
                                                  @Param("referencePeriod") String referencePeriod);

    TagImpactRangeRecord selectImpactRange(@Param("tagName") String tagName,
                                           @Param("scopeDimension") String scopeDimension);

    TagTriggerLog selectTriggerLog(@Param("tagName") String tagName,
                                   @Param("monitoringWindow") String monitoringWindow);

    TagEffectivenessRecord selectEffectiveness(@Param("tagName") String tagName,
                                               @Param("evaluationDate") String evaluationDate);

    List<TagFeedbackRecord> selectFeedback(@Param("tagName") String tagName,
                                           @Param("feedbackPeriod") String feedbackPeriod);
}
