package java.mapper;

import java.entity.TagUsageRecord;
import java.util.List;

public interface TagMapper {

    TagUsageRecord save(TagUsageRecord record);

    List<TagUsageRecord> findByTag(String tagCode);
}
