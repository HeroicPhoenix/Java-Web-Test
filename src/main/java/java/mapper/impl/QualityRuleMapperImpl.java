package java.mapper.impl;

import java.entity.QualityRule;
import java.mapper.QualityRuleMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class QualityRuleMapperImpl implements QualityRuleMapper {

    private final ConcurrentHashMap<Long, QualityRule> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public QualityRule save(QualityRule rule) {
        if (rule.getId() == null) {
            rule.setId(sequence.getAndIncrement());
        }
        storage.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public List<QualityRule> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<QualityRule> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }
}
