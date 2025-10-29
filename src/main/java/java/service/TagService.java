package java.service;

import java.dto.TagRequest;
import java.entity.TagUsageRecord;
import java.util.List;

public interface TagService {

    TagUsageRecord recordUsageCount(TagRequest request);

    List<TagUsageRecord> analyzeUsageFrequency(TagRequest request);

    List<TagUsageRecord> analyzeInfluenceRange(TagRequest request);

    TagUsageRecord monitorTrigger(TagRequest request);

    TagUsageRecord evaluateEffect(TagRequest request);

    TagUsageRecord collectFeedback(TagRequest request);
}
