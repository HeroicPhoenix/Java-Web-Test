package com.lvwyh.service.impl;

import com.lvwyh.ao.AuthoritySourceCatalogAO;
import com.lvwyh.ao.IndicatorTraceRequestAO;
import com.lvwyh.entity.AuthoritySourceEntry;
import com.lvwyh.entity.IndicatorLineageRecord;
import com.lvwyh.mapper.DataSourceManagementMapper;
import com.lvwyh.service.DataSourceManagementService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSourceManagementServiceImpl implements DataSourceManagementService {

    private final DataSourceManagementMapper dataSourceManagementMapper;

    public DataSourceManagementServiceImpl(DataSourceManagementMapper dataSourceManagementMapper) {
        this.dataSourceManagementMapper = dataSourceManagementMapper;
    }

    @Override
    public Map<String, Object> listAuthoritySources(AuthoritySourceCatalogAO request) {
        int pageNo = request.getPageNo().intValue();
        int pageSize = request.getPageSize().intValue();
        int offset = (pageNo - 1) * pageSize;
        List<AuthoritySourceEntry> entries = dataSourceManagementMapper.selectAuthoritySources(
                request.getDomainName(), request.getOwner(), offset, pageSize);
        int total = dataSourceManagementMapper.countAuthoritySources(request.getDomainName(), request.getOwner());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("total", total);
        result.put("entries", entries);
        return result;
    }

    @Override
    public Map<String, Object> traceIndicatorSource(IndicatorTraceRequestAO request) {
        List<IndicatorLineageRecord> list = dataSourceManagementMapper.selectIndicatorLineage(
                request.getIndicatorCode(), request.getIndicatorName(), request.getTraceDepth());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", Boolean.TRUE);
        result.put("lineage", list);
        return result;
    }
}
