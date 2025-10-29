package java.service.impl;

import java.dto.MetadataQueryRequest;
import java.entity.MetadataRecord;
import java.exception.BusinessException;
import java.mapper.MetadataMapper;
import java.service.MetadataService;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class MetadataServiceImpl implements MetadataService {

    private final MetadataMapper mapper;

    public MetadataServiceImpl(MetadataMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void initData() {
        MetadataRecord record = new MetadataRecord();
        record.setDataSource("ODS_MARKETING");
        record.setStructure("表：customer_usage");
        record.setAttributes("customer_id, usage, fee");
        record.setRelations("关联 customer");
        mapper.insert(record);
    }

    @Override
    public List<MetadataRecord> displayCatalog() {
        return mapper.findAll();
    }

    @Override
    public List<MetadataRecord> queryMetadata(MetadataQueryRequest request) {
        validateRequest(request);
        String keyword = request.getKeyword().toLowerCase(Locale.ROOT);
        return mapper.findAll().stream()
                .filter(item -> item.getStructure().toLowerCase(Locale.ROOT).contains(keyword)
                        || item.getAttributes().toLowerCase(Locale.ROOT).contains(keyword)
                        || item.getRelations().toLowerCase(Locale.ROOT).contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public String exportMetadata(MetadataQueryRequest request) {
        validateRequest(request);
        StringBuilder builder = new StringBuilder();
        List<MetadataRecord> records = queryMetadata(request);
        for (MetadataRecord record : records) {
            builder.append(record.getId()).append(',')
                    .append(record.getDataSource()).append(',')
                    .append(record.getStructure()).append('\n');
        }
        return builder.toString();
    }

    private void validateRequest(MetadataQueryRequest request) {
        if (request == null || request.getKeyword() == null || request.getKeyword().trim().isEmpty()) {
            throw new BusinessException("Keyword is required");
        }
    }
}
