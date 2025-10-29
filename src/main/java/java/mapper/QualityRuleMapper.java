package java.mapper;

import java.entity.QualityRule;
import java.util.List;
import java.util.Optional;

public interface QualityRuleMapper {

    QualityRule save(QualityRule rule);

    List<QualityRule> findAll();

    Optional<QualityRule> findById(Long id);

    void delete(Long id);
}
