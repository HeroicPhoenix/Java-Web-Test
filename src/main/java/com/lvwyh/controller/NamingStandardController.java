package com.lvwyh.controller;

import com.lvwyh.ao.NodeNamingCheckAO;
import com.lvwyh.ao.ProjectSpaceNamingCheckAO;
import com.lvwyh.ao.TableNamingCheckAO;
import com.lvwyh.service.NamingStandardService;
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
@RequestMapping("/api/naming-standard")
@Tag(name = "命名规范校验", description = "命名规范校验接口")
public class NamingStandardController {

    private final NamingStandardService namingStandardService;

    public NamingStandardController(NamingStandardService namingStandardService) {
        this.namingStandardService = namingStandardService;
    }

    @PostMapping("/table")
    @Operation(summary = "表模型命名规范校验")
    public Map<String, Object> checkTableNaming(@RequestBody TableNamingCheckAO request,
                                                HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getTableName() == null || request.getTableName().trim().length() == 0) {
            return buildError(response, "表模型命名入参不合法");
        }
        return namingStandardService.checkTableNaming(request);
    }

    @PostMapping("/node")
    @Operation(summary = "节点命名规范校验")
    public Map<String, Object> checkNodeNaming(@RequestBody NodeNamingCheckAO request,
                                               HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getNodeName() == null || request.getNodeName().trim().length() == 0) {
            return buildError(response, "节点命名入参不合法");
        }
        return namingStandardService.checkNodeNaming(request);
    }

    @PostMapping("/project-space")
    @Operation(summary = "项目空间命名规范")
    public Map<String, Object> checkProjectSpace(@RequestBody ProjectSpaceNamingCheckAO request,
                                                 HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getSpaceName() == null || request.getSpaceName().trim().length() == 0) {
            return buildError(response, "项目空间命名入参不合法");
        }
        return namingStandardService.checkProjectSpaceNaming(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
