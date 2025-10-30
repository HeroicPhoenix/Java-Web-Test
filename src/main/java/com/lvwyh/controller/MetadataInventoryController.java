package com.lvwyh.controller;

import com.lvwyh.ao.MetadataExportAO;
import com.lvwyh.ao.MetadataListAO;
import com.lvwyh.ao.MetadataQueryAO;
import com.lvwyh.service.MetadataInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/metadata-inventory")
@Tag(name = "元数据清单", description = "元数据清单展示、查询、导出接口")
public class MetadataInventoryController {


    @Autowired
    private MetadataInventoryService metadataInventoryService;

    @PostMapping("/list")
    @Operation(summary = "元数据清单展示")
    public Map<String, Object> displayMetadataList(@RequestBody MetadataListAO request,
                                                   HttpServletResponse response) {
        if (request == null || request.getPageNo() == null || request.getPageNo() <= 0
                || request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 200) {
            return buildError(response, "元数据分页参数不合法");
        }
        return metadataInventoryService.displayMetadataList(request);
    }

    @PostMapping("/query")
    @Operation(summary = "元数据查询")
    public Map<String, Object> queryMetadata(@RequestBody MetadataQueryAO request,
                                             HttpServletResponse response) {
        if (request == null || request.getKeyword() == null || request.getKeyword().trim().length() < 2) {
            return buildError(response, "元数据查询关键字不合法");
        }
        return metadataInventoryService.queryMetadata(request);
    }

    @PostMapping("/export")
    @Operation(summary = "元数据导出")
    public Map<String, Object> exportMetadata(@RequestBody MetadataExportAO request,
                                              HttpServletResponse response) {
        if (request == null || request.getExportFormat() == null || request.getExportFormat().trim().length() == 0) {
            return buildError(response, "导出格式不能为空");
        }
        return metadataInventoryService.exportMetadata(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
