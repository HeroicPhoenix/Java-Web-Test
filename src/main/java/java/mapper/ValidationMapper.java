package java.mapper;

import java.entity.ValidationRecord;
import java.util.List;

public interface ValidationMapper {

    ValidationRecord save(ValidationRecord record);

    List<ValidationRecord> findByCategory(String category);
}
