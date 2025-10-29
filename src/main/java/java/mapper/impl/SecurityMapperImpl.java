package java.mapper.impl;

import java.entity.SecurityRule;
import java.mapper.SecurityMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityMapperImpl implements SecurityMapper {

    private final ConcurrentHashMap<Long, SecurityRule> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public SecurityRule save(SecurityRule rule) {
        if (rule.getId() == null) {
            rule.setId(sequence.getAndIncrement());
        }
        storage.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public List<SecurityRule> findByType(String type) {
        List<SecurityRule> result = new ArrayList<>();
        for (SecurityRule rule : storage.values()) {
            if (rule.getRuleType().equalsIgnoreCase(type)) {
                result.add(rule);
            }
        }
        return result;
    }
}
