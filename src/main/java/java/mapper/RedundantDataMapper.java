package java.mapper;

import java.entity.RedundantDataRecord;
import java.util.List;

public interface RedundantDataMapper {

    RedundantDataRecord save(RedundantDataRecord record);

    List<RedundantDataRecord> findAll();
}
