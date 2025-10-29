package java.service;

import java.dto.MetadataQueryRequest;
import java.entity.MetadataRecord;
import java.util.List;

public interface MetadataService {

    List<MetadataRecord> displayCatalog();

    List<MetadataRecord> queryMetadata(MetadataQueryRequest request);

    String exportMetadata(MetadataQueryRequest request);
}
