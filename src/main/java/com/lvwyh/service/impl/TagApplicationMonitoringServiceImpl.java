package com.lvwyh.service.impl;

import com.lvwyh.ao.TagEffectivenessAO;
import com.lvwyh.ao.TagImpactScopeAO;
import com.lvwyh.ao.TagTriggerStatusAO;
import com.lvwyh.ao.TagUsageCountAO;
import com.lvwyh.ao.TagUsageFrequencyAO;
import com.lvwyh.ao.TagUserFeedbackAO;
import com.lvwyh.entity.TagEffectivenessRecord;
import com.lvwyh.entity.TagFeedbackRecord;
import com.lvwyh.entity.TagImpactRangeRecord;
import com.lvwyh.entity.TagTriggerLog;
import com.lvwyh.entity.TagUsageFrequencyRecord;
import com.lvwyh.entity.TagUsageSummary;
import com.lvwyh.mapper.TagApplicationMonitoringMapper;
import com.lvwyh.service.TagApplicationMonitoringService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagApplicationMonitoringServiceImpl implements TagApplicationMonitoringService {

    private final TagApplicationMonitoringMapper tagApplicationMonitoringMapper;

    public TagApplicationMonitoringServiceImpl(TagApplicationMonitoringMapper tagApplicationMonitoringMapper) {
        this.tagApplicationMonitoringMapper = tagApplicationMonitoringMapper;
    }

    @Override
    public Map<String, Object> analyzeUsageCount(TagUsageCountAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        TagUsageSummary summary = tagApplicationMonitoringMapper.selectUsageSummary(request.getTagName(),
                request.getStartDate(), request.getEndDate());
        result.put("success", Boolean.TRUE);
        result.put("summary", summary);
        return result;
    }

    @Override
    public Map<String, Object> analyzeUsageFrequency(TagUsageFrequencyAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        TagUsageFrequencyRecord record = tagApplicationMonitoringMapper.selectFrequencyRecord(request.getTagName(),
                request.getFrequencyType(), request.getReferencePeriod());
        result.put("success", Boolean.TRUE);
        result.put("frequency", record);
        return result;
    }

    @Override
    public Map<String, Object> analyzeImpactScope(TagImpactScopeAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        TagImpactRangeRecord rangeRecord = tagApplicationMonitoringMapper.selectImpactRange(request.getTagName(),
                request.getScopeDimension());
        result.put("success", Boolean.TRUE);
        result.put("impact", rangeRecord);
        return result;
    }

    @Override
    public Map<String, Object> monitorTriggerStatus(TagTriggerStatusAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        TagTriggerLog triggerLog = tagApplicationMonitoringMapper.selectTriggerLog(request.getTagName(),
                request.getMonitoringWindow());
        result.put("success", Boolean.TRUE);
        result.put("triggerStatus", triggerLog);
        return result;
    }

    @Override
    public Map<String, Object> evaluateEffectiveness(TagEffectivenessAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        TagEffectivenessRecord effectivenessRecord = tagApplicationMonitoringMapper.selectEffectiveness(request.getTagName(),
                request.getEvaluationDate());
        result.put("success", Boolean.TRUE);
        result.put("effectiveness", effectivenessRecord);
        return result;
    }

    @Override
    public Map<String, Object> collectUserFeedback(TagUserFeedbackAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TagFeedbackRecord> records = tagApplicationMonitoringMapper.selectFeedback(request.getTagName(),
                request.getFeedbackPeriod());
        result.put("success", Boolean.TRUE);
        result.put("feedback", records);
        return result;
    }
}
