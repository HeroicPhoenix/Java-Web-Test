package com.lvwyh.controller;

import com.lvwyh.ao.NamingAdaptationAO;
import com.lvwyh.service.NamingAdaptationService;
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
@RequestMapping("/api/naming-adaptation")
@Tag(name = "命名适配", description = "自定义命名适配接口")
public class NamingAdaptationController {


    @Autowired
    private NamingAdaptationService namingAdaptationService;

    @PostMapping("/custom-rule")
    @Operation(summary = "自定义命名适配规则")
    public Map<String, Object> adaptName(@RequestBody NamingAdaptationAO request,
                                         HttpServletResponse response) {
        if (request == null || request.getRuleCode() == null || request.getRuleCode().trim().length() == 0
                || request.getOriginalName() == null || request.getOriginalName().trim().length() == 0) {
            return buildError(response, "命名适配入参不合法");
        }
        return namingAdaptationService.adaptName(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
