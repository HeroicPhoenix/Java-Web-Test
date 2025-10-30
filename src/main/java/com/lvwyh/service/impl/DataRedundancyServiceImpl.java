package com.lvwyh.service.impl;

import com.lvwyh.ao.RedundantDataExportAO;
import com.lvwyh.entity.RedundantDataRecord;
import com.lvwyh.mapper.DataRedundancyMapper;
import com.lvwyh.service.DataRedundancyService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataRedundancyServiceImpl implements DataRedundancyService {

    private final DataRedundancyMapper dataRedundancyMapper;

    public DataRedundancyServiceImpl(DataRedundancyMapper dataRedundancyMapper) {
        this.dataRedundancyMapper = dataRedundancyMapper;
    }

    @Override
    public Map<String, Object> exportRedundantData(RedundantDataExportAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        int pageNo = request.getIncludeResolved() != null && request.getIncludeResolved().booleanValue() ? 1 : 1;
        int pageSize = 200;
        if (request.getOutputFormat() != null && "CSV".equalsIgnoreCase(request.getOutputFormat())) {
            pageSize = 500;
        }
        int total = dataRedundancyMapper.countRedundantRecords(request.getDomainName(),
                request.getIncludeResolved() != null && request.getIncludeResolved().booleanValue());
        List<RedundantDataRecord> records = dataRedundancyMapper.selectRedundantRecords(request.getDomainName(),
                request.getIncludeResolved() != null && request.getIncludeResolved().booleanValue(),
                (pageNo - 1) * pageSize, pageSize);
        result.put("success", Boolean.TRUE);
        result.put("total", total);
        result.put("exportFormat", request.getOutputFormat());
        result.put("records", records);
        return result;
    }
}
