package com.lvwyh.service;

import com.lvwyh.ao.RedundantDataExportAO;

import java.util.Map;

public interface DataRedundancyService {
    Map<String, Object> exportRedundantData(RedundantDataExportAO request);
}
