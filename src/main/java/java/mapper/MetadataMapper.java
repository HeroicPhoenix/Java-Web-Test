package java.mapper;

import java.entity.MetadataRecord;
import java.util.List;

public interface MetadataMapper {

    MetadataRecord insert(MetadataRecord record);

    List<MetadataRecord> findAll();
}
