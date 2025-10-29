package java.mapper;

import java.entity.DataLifecycleRecord;
import java.util.List;

public interface DataLifecycleMapper {

    DataLifecycleRecord save(DataLifecycleRecord record);

    List<DataLifecycleRecord> findByDataset(String datasetName);
}
