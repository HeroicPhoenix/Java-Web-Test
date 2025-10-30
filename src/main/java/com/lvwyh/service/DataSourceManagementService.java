package com.lvwyh.service;

import com.lvwyh.ao.AuthoritySourceCatalogAO;
import com.lvwyh.ao.IndicatorTraceRequestAO;

import java.util.Map;

public interface DataSourceManagementService {
    Map<String, Object> listAuthoritySources(AuthoritySourceCatalogAO request);

    Map<String, Object> traceIndicatorSource(IndicatorTraceRequestAO request);
}
