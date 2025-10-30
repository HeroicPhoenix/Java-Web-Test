package com.lvwyh.service;

import com.lvwyh.ao.MetadataExportAO;
import com.lvwyh.ao.MetadataListAO;
import com.lvwyh.ao.MetadataQueryAO;

import java.util.Map;

public interface MetadataInventoryService {
    Map<String, Object> displayMetadataList(MetadataListAO request);

    Map<String, Object> queryMetadata(MetadataQueryAO request);

    Map<String, Object> exportMetadata(MetadataExportAO request);
}
