package java.mapper;

import java.entity.SecurityRule;
import java.util.List;

public interface SecurityMapper {

    SecurityRule save(SecurityRule rule);

    List<SecurityRule> findByType(String type);
}
