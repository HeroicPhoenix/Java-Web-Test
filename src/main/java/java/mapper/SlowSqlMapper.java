package java.mapper;

import java.entity.SlowSqlRecord;
import java.util.List;

public interface SlowSqlMapper {

    SlowSqlRecord save(SlowSqlRecord record);

    List<SlowSqlRecord> findByCategory(String category);
}
