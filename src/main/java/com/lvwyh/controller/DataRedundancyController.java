package com.lvwyh.controller;

import com.lvwyh.ao.RedundantDataExportAO;
import com.lvwyh.service.DataRedundancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data-redundancy")
@Tag(name = "冗余数据识别", description = "冗余数据清单导出接口")
public class DataRedundancyController {

    private final DataRedundancyService dataRedundancyService;

    public DataRedundancyController(DataRedundancyService dataRedundancyService) {
        this.dataRedundancyService = dataRedundancyService;
    }

    @PostMapping("/export")
    @Operation(summary = "导出相关冗余数据清单")
    public Map<String, Object> exportRedundantData(@RequestBody RedundantDataExportAO request,
                                                   HttpServletResponse response) {
        if (request == null || request.getDomainName() == null || request.getDomainName().trim().length() == 0) {
            return buildError(response, "领域名称不能为空");
        }
        if (request.getOutputFormat() == null || request.getOutputFormat().trim().length() == 0) {
            return buildError(response, "导出格式不能为空");
        }
        String format = request.getOutputFormat().toUpperCase();
        if (!"JSON".equals(format) && !"CSV".equals(format)) {
            return buildError(response, "导出格式只支持JSON或CSV");
        }
        return dataRedundancyService.exportRedundantData(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
