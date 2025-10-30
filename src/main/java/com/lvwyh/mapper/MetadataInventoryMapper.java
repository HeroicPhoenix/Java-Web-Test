package com.lvwyh.mapper;

import com.lvwyh.entity.MetadataItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MetadataInventoryMapper {
    List<MetadataItem> selectMetadataItems(@Param("dataSource") String dataSource,
                                           @Param("offset") int offset,
                                           @Param("limit") int limit);

    int countMetadataItems(@Param("dataSource") String dataSource);

    List<MetadataItem> searchMetadataItems(@Param("keyword") String keyword,
                                           @Param("dataSource") String dataSource);
}
