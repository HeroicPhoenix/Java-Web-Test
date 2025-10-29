package java.service.impl;

import java.dto.TagRequest;
import java.entity.TagUsageRecord;
import java.exception.BusinessException;
import java.mapper.TagMapper;
import java.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper mapper;

    public TagServiceImpl(TagMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TagUsageRecord recordUsageCount(TagRequest request) {
        return save(request, "COUNT");
    }

    @Override
    public List<TagUsageRecord> analyzeUsageFrequency(TagRequest request) {
        return simulate(request, "FREQUENCY", 0.8);
    }

    @Override
    public List<TagUsageRecord> analyzeInfluenceRange(TagRequest request) {
        return simulate(request, "INFLUENCE", 1.5);
    }

    @Override
    public TagUsageRecord monitorTrigger(TagRequest request) {
        return save(request, "TRIGGER");
    }

    @Override
    public TagUsageRecord evaluateEffect(TagRequest request) {
        return save(request, "EFFECT");
    }

    @Override
    public TagUsageRecord collectFeedback(TagRequest request) {
        return save(request, "FEEDBACK");
    }

    private TagUsageRecord save(TagRequest request, String metric) {
        validate(request);
        TagUsageRecord record = new TagUsageRecord();
        record.setTagCode(request.getTagCode());
        record.setMetric(metric);
        record.setValue(request.getValue() != null ? request.getValue() : 1d);
        return mapper.save(record);
    }

    private List<TagUsageRecord> simulate(TagRequest request, String metric, double factor) {
        validate(request);
        List<TagUsageRecord> base = mapper.findByTag(request.getTagCode());
        if (base.isEmpty()) {
            base.add(save(request, metric));
        }
        return base.stream().map(item -> {
            TagUsageRecord record = new TagUsageRecord();
            record.setTagCode(item.getTagCode());
            record.setMetric(metric);
            record.setValue((request.getValue() != null ? request.getValue() : 1d) * factor);
            return record;
        }).collect(Collectors.toList());
    }

    private void validate(TagRequest request) {
        if (request == null || request.getTagCode() == null || request.getTagCode().trim().isEmpty()) {
            throw new BusinessException("Tag code is required");
        }
    }
}
