package java.service;

import java.dto.ValidationRequest;
import java.entity.ValidationRecord;
import java.util.List;

public interface ValidationService {

    ValidationRecord validateBusinessRule(ValidationRequest request);

    ValidationRecord validateDataRule(ValidationRequest request);

    ValidationRecord checkTableStandard(ValidationRequest request);

    ValidationRecord checkFieldType(ValidationRequest request);

    ValidationRecord checkLineageStandard(ValidationRequest request);

    ValidationRecord checkCodingStandard(ValidationRequest request);

    ValidationRecord checkLayeringStandard(ValidationRequest request);

    ValidationRecord checkSchedulingStandard(ValidationRequest request);

    ValidationRecord checkIntegrationStandard(ValidationRequest request);

    ValidationRecord checkTableNaming(ValidationRequest request);

    ValidationRecord checkNodeNaming(ValidationRequest request);

    ValidationRecord checkProjectNaming(ValidationRequest request);

    ValidationRecord adaptNamingRule(ValidationRequest request);

    List<ValidationRecord> findByCategory(String category);
}
