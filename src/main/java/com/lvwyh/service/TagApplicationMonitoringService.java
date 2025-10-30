package com.lvwyh.service;

import com.lvwyh.ao.TagEffectivenessAO;
import com.lvwyh.ao.TagImpactScopeAO;
import com.lvwyh.ao.TagTriggerStatusAO;
import com.lvwyh.ao.TagUsageCountAO;
import com.lvwyh.ao.TagUsageFrequencyAO;
import com.lvwyh.ao.TagUserFeedbackAO;

import java.util.Map;

public interface TagApplicationMonitoringService {
    Map<String, Object> analyzeUsageCount(TagUsageCountAO request);

    Map<String, Object> analyzeUsageFrequency(TagUsageFrequencyAO request);

    Map<String, Object> analyzeImpactScope(TagImpactScopeAO request);

    Map<String, Object> monitorTriggerStatus(TagTriggerStatusAO request);

    Map<String, Object> evaluateEffectiveness(TagEffectivenessAO request);

    Map<String, Object> collectUserFeedback(TagUserFeedbackAO request);
}
