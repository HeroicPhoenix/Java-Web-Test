package com.lvwyh.controller;

import com.lvwyh.ao.AuthoritySourceCatalogAO;
import com.lvwyh.ao.IndicatorTraceRequestAO;
import com.lvwyh.service.DataSourceManagementService;
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
@RequestMapping("/api/data-source-management")
@Tag(name = "数据定源管理", description = "数据权威源目录及指标溯源接口")
public class DataSourceManagementController {

    private final DataSourceManagementService dataSourceManagementService;

    public DataSourceManagementController(DataSourceManagementService dataSourceManagementService) {
        this.dataSourceManagementService = dataSourceManagementService;
    }

    @PostMapping("/authority-catalog")
    @Operation(summary = "数据权威源目录清单")
    public Map<String, Object> listAuthorityCatalog(@RequestBody AuthoritySourceCatalogAO request,
                                                    HttpServletResponse response) {
        if (request == null || request.getDomainName() == null || request.getDomainName().trim().length() == 0) {
            return buildError(response, "领域名称不能为空");
        }
        if (request.getPageNo() == null || request.getPageNo().intValue() <= 0) {
            return buildError(response, "页码必须大于0");
        }
        if (request.getPageSize() == null || request.getPageSize().intValue() <= 0 || request.getPageSize().intValue() > 200) {
            return buildError(response, "分页大小范围应在1-200");
        }
        return dataSourceManagementService.listAuthoritySources(request);
    }

    @PostMapping("/indicator-trace")
    @Operation(summary = "营销指标的数据来源追溯")
    public Map<String, Object> traceIndicator(@RequestBody IndicatorTraceRequestAO request,
                                              HttpServletResponse response) {
        if (request == null) {
            return buildError(response, "请求不能为空");
        }
        if ((request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0)
                && (request.getIndicatorName() == null || request.getIndicatorName().trim().length() == 0)) {
            return buildError(response, "指标编码或名称至少提供一个");
        }
        return dataSourceManagementService.traceIndicatorSource(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
