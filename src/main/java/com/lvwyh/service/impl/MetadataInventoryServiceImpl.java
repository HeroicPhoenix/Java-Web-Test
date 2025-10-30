package com.lvwyh.service.impl;

import com.lvwyh.ao.MetadataExportAO;
import com.lvwyh.ao.MetadataListAO;
import com.lvwyh.ao.MetadataQueryAO;
import com.lvwyh.entity.MetadataItem;
import com.lvwyh.mapper.MetadataInventoryMapper;
import com.lvwyh.service.MetadataInventoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetadataInventoryServiceImpl implements MetadataInventoryService {

    private final MetadataInventoryMapper metadataInventoryMapper;

    public MetadataInventoryServiceImpl(MetadataInventoryMapper metadataInventoryMapper) {
        this.metadataInventoryMapper = metadataInventoryMapper;
    }

    @Override
    public Map<String, Object> displayMetadataList(MetadataListAO request) {
        int pageNo = request.getPageNo() == null ? 1 : request.getPageNo();
        int pageSize = request.getPageSize() == null ? 10 : request.getPageSize();
        int offset = (pageNo - 1) * pageSize;
        List<MetadataItem> items = metadataInventoryMapper.selectMetadataItems(request.getDataSource(), offset, pageSize);
        int total = metadataInventoryMapper.countMetadataItems(request.getDataSource());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("records", items);
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> queryMetadata(MetadataQueryAO request) {
        List<MetadataItem> items = metadataInventoryMapper.searchMetadataItems(request.getKeyword(), request.getDataSource());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("records", items);
        result.put("count", items.size());
        return result;
    }

    @Override
    public Map<String, Object> exportMetadata(MetadataExportAO request) {
        List<MetadataItem> items = metadataInventoryMapper.searchMetadataItems(request.getKeyword(), null);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("exportFormat", request.getExportFormat());
        result.put("recordCount", items.size());
        result.put("records", items);
        return result;
    }
}
