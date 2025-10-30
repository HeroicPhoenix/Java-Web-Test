package com.lvwyh.mapper;

import com.lvwyh.entity.RedundantDataRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataRedundancyMapper {
    List<RedundantDataRecord> selectRedundantRecords(@Param("domainName") String domainName,
                                                     @Param("includeResolved") boolean includeResolved,
                                                     @Param("offset") int offset,
                                                     @Param("limit") int limit);

    int countRedundantRecords(@Param("domainName") String domainName,
                              @Param("includeResolved") boolean includeResolved);
}
