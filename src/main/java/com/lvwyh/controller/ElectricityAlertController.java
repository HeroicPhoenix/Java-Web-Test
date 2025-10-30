package com.lvwyh.controller;

import com.lvwyh.ao.ElectricityForecastAO;
import com.lvwyh.ao.HighPriceWarningAO;
import com.lvwyh.service.ElectricityAlertService;
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
@RequestMapping("/api/electricity-alert")
@Tag(name = "电价预警", description = "电量电费预测与高价用户预警接口")
public class ElectricityAlertController {

    private final ElectricityAlertService electricityAlertService;

    public ElectricityAlertController(ElectricityAlertService electricityAlertService) {
        this.electricityAlertService = electricityAlertService;
    }

    @PostMapping("/forecast")
    @Operation(summary = "未来电量电费数据预测")
    public Map<String, Object> forecast(@RequestBody ElectricityForecastAO request,
                                        HttpServletResponse response) {
        if (request == null || request.getUserId() == null || request.getUserId() <= 0
                || request.getMonths() == null || request.getMonths() <= 0 || request.getMonths() > 12) {
            return buildError(response, "预测入参不合法");
        }
        return electricityAlertService.forecastElectricityCost(request);
    }

    @PostMapping("/warning")
    @Operation(summary = "对电价过高用户预警")
    public Map<String, Object> warning(@RequestBody HighPriceWarningAO request,
                                       HttpServletResponse response) {
        if (request == null || request.getUserId() == null || request.getUserId() <= 0
                || request.getForecastCost() == null || request.getForecastCost() <= 0) {
            return buildError(response, "预警入参不合法");
        }
        return electricityAlertService.warnHighPriceUser(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }
}
