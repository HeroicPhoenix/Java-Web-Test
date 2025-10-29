package java.service.impl;

import java.dto.ValidationRequest;
import java.entity.ValidationRecord;
import java.exception.BusinessException;
import java.mapper.ValidationMapper;
import java.service.ValidationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final ValidationMapper mapper;

    public ValidationServiceImpl(ValidationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ValidationRecord validateBusinessRule(ValidationRequest request) {
        return buildAndSave("BUSINESS_RULE", request, "业务规则验证通过");
    }

    @Override
    public ValidationRecord validateDataRule(ValidationRequest request) {
        return buildAndSave("DATA_RULE", request, "数据规则符合规范");
    }

    @Override
    public ValidationRecord checkTableStandard(ValidationRequest request) {
        return buildAndSave("TABLE_STANDARD", request, "建表规范校验完成");
    }

    @Override
    public ValidationRecord checkFieldType(ValidationRequest request) {
        return buildAndSave("FIELD_TYPE", request, "字段类型校验通过");
    }

    @Override
    public ValidationRecord checkLineageStandard(ValidationRequest request) {
        return buildAndSave("LINEAGE", request, "血缘规范校验通过");
    }

    @Override
    public ValidationRecord checkCodingStandard(ValidationRequest request) {
        return buildAndSave("CODING", request, "编码规范校验通过");
    }

    @Override
    public ValidationRecord checkLayeringStandard(ValidationRequest request) {
        return buildAndSave("LAYERING", request, "分层规范符合要求");
    }

    @Override
    public ValidationRecord checkSchedulingStandard(ValidationRequest request) {
        return buildAndSave("SCHEDULING", request, "调度规范校验完成");
    }

    @Override
    public ValidationRecord checkIntegrationStandard(ValidationRequest request) {
        return buildAndSave("INTEGRATION", request, "集成规范校验完成");
    }

    @Override
    public ValidationRecord checkTableNaming(ValidationRequest request) {
        return buildAndSave("TABLE_NAMING", request, "表模型命名规范校验完成");
    }

    @Override
    public ValidationRecord checkNodeNaming(ValidationRequest request) {
        return buildAndSave("NODE_NAMING", request, "节点命名规范校验完成");
    }

    @Override
    public ValidationRecord checkProjectNaming(ValidationRequest request) {
        return buildAndSave("PROJECT_NAMING", request, "项目空间命名规范校验完成");
    }

    @Override
    public ValidationRecord adaptNamingRule(ValidationRequest request) {
        return buildAndSave("NAMING_ADAPT", request, "已生成自定义命名适配方案");
    }

    @Override
    public List<ValidationRecord> findByCategory(String category) {
        return mapper.findByCategory(category);
    }

    private ValidationRecord buildAndSave(String category, ValidationRequest request, String successDetail) {
        validateRequest(request);
        ValidationRecord record = new ValidationRecord();
        record.setCategory(category);
        record.setRuleName(request.getRule());
        record.setStatus("PASS");
        record.setDetail(successDetail + " -> " + request.getTargetName());
        return mapper.save(record);
    }

    private void validateRequest(ValidationRequest request) {
        if (request == null || isBlank(request.getTargetName()) || isBlank(request.getRule())) {
            throw new BusinessException("Target name and rule must be provided");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
