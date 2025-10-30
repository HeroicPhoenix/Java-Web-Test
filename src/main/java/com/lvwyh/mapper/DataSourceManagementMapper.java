package com.lvwyh.mapper;

import com.lvwyh.entity.AuthoritySourceEntry;
import com.lvwyh.entity.IndicatorLineageRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSourceManagementMapper {
    List<AuthoritySourceEntry> selectAuthoritySources(@Param("domainName") String domainName,
                                                      @Param("owner") String owner,
                                                      @Param("offset") int offset,
                                                      @Param("limit") int limit);

    int countAuthoritySources(@Param("domainName") String domainName,
                              @Param("owner") String owner);

    List<IndicatorLineageRecord> selectIndicatorLineage(@Param("indicatorCode") String indicatorCode,
                                                        @Param("indicatorName") String indicatorName,
                                                        @Param("traceDepth") String traceDepth);
}
