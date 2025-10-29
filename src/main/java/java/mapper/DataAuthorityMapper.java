package java.mapper;

import java.entity.DataAuthorityRecord;
import java.util.List;

public interface DataAuthorityMapper {

    DataAuthorityRecord save(DataAuthorityRecord record);

    List<DataAuthorityRecord> findByDataset(String datasetName);
}
