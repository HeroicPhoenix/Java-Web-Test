package com.lvwyh.controller;

import com.lvwyh.ao.IndicatorComparisonMoMAO;
import com.lvwyh.ao.IndicatorComparisonYoYAO;
import com.lvwyh.ao.IndicatorHistoryQueryAO;
import com.lvwyh.ao.IndicatorTrendDailyAO;
import com.lvwyh.ao.IndicatorTrendMonthlyAO;
import com.lvwyh.ao.IndicatorTrendWeeklyAO;
import com.lvwyh.ao.RealtimeMonitoringAO;
import com.lvwyh.service.MarketingIndicatorMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/indicator-monitoring")
@Tag(name = "营销指标运营监控", description = "指标历史、趋势、对比及实时监控接口")
public class MarketingIndicatorMonitoringController {

    @Autowired
    private MarketingIndicatorMonitoringService marketingIndicatorMonitoringService;

    @PostMapping("/history")
    @Operation(summary = "查询分析指定历史时点的营销业务指标")
    public Map<String, Object> queryHistory(@RequestBody IndicatorHistoryQueryAO request,
                                            HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        if (request.getStartDate() == null || request.getStartDate().trim().length() == 0
                || request.getEndDate() == null || request.getEndDate().trim().length() == 0) {
            return buildError(response, "请提供开始和结束日期");
        }
        return marketingIndicatorMonitoringService.queryIndicatorHistory(request);
    }

    @PostMapping("/trend/day")
    @Operation(summary = "业务指标趋势变化分析-日")
    public Map<String, Object> analyzeDaily(@RequestBody IndicatorTrendDailyAO request,
                                            HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        if (request.getRecentDays() == null || request.getRecentDays().intValue() <= 0 || request.getRecentDays().intValue() > 90) {
            return buildError(response, "日趋势分析仅支持1-90天");
        }
        return marketingIndicatorMonitoringService.analyzeDailyTrend(request);
    }

    @PostMapping("/trend/week")
    @Operation(summary = "业务指标趋势变化分析-周")
    public Map<String, Object> analyzeWeekly(@RequestBody IndicatorTrendWeeklyAO request,
                                             HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        if (request.getRecentWeeks() == null || request.getRecentWeeks().intValue() <= 0
                || request.getRecentWeeks().intValue() > 52) {
            return buildError(response, "周趋势分析仅支持1-52周");
        }
        return marketingIndicatorMonitoringService.analyzeWeeklyTrend(request);
    }

    @PostMapping("/trend/month")
    @Operation(summary = "业务指标趋势变化分析-月")
    public Map<String, Object> analyzeMonthly(@RequestBody IndicatorTrendMonthlyAO request,
                                              HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        if (request.getRecentMonths() == null || request.getRecentMonths().intValue() <= 0
                || request.getRecentMonths().intValue() > 36) {
            return buildError(response, "月趋势分析仅支持1-36个月");
        }
        return marketingIndicatorMonitoringService.analyzeMonthlyTrend(request);
    }

    @PostMapping("/compare/yoy")
    @Operation(summary = "业务指标与历史数据的对比-同比")
    public Map<String, Object> compareYoY(@RequestBody IndicatorComparisonYoYAO request,
                                          HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        LocalDate currentDate = parseDate(request.getCurrentPeriod());
        if (currentDate == null) {
            return buildError(response, "当前日期格式需为yyyy-MM-dd");
        }
        LocalDate expectedPrevious = currentDate.minusYears(1);
        if (request.getPreviousPeriod() != null && request.getPreviousPeriod().trim().length() > 0) {
            LocalDate provided = parseDate(request.getPreviousPeriod());
            if (provided == null || !provided.equals(expectedPrevious)) {
                return buildError(response, "同比日期必须为当前日期的去年同一天");
            }
        }
        request.setCurrentPeriod(currentDate.toString());
        request.setPreviousPeriod(expectedPrevious.toString());
        return marketingIndicatorMonitoringService.compareYearOverYear(request);
    }

    @PostMapping("/compare/mom")
    @Operation(summary = "业务指标与历史数据的对比-环比")
    public Map<String, Object> compareMoM(@RequestBody IndicatorComparisonMoMAO request,
                                          HttpServletResponse response) {
        if (request == null || request.getIndicatorCode() == null || request.getIndicatorCode().trim().length() == 0) {
            return buildError(response, "指标编码不能为空");
        }
        LocalDate currentDate = parseDate(request.getCurrentPeriod());
        if (currentDate == null) {
            return buildError(response, "当前日期格式需为yyyy-MM-dd");
        }
        LocalDate expectedPrevious = currentDate.minusDays(1);
        if (request.getPreviousPeriod() != null && request.getPreviousPeriod().trim().length() > 0) {
            LocalDate provided = parseDate(request.getPreviousPeriod());
            if (provided == null || !provided.equals(expectedPrevious)) {
                return buildError(response, "环比日期必须为当前日期的前一天");
            }
        }
        request.setCurrentPeriod(currentDate.toString());
        request.setPreviousPeriod(expectedPrevious.toString());
        return marketingIndicatorMonitoringService.compareMonthOverMonth(request);
    }

    @PostMapping("/realtime")
    @Operation(summary = "营销业务指标实时监控分析-指标分类维度（按如下维度分类：指标分类、指标实时数据、关键业务指标、异常检测告警、业务责任主体、应用范围、系统调用情况、关注度访问量）")
    public Map<String, Object> realtimeMonitoring(@RequestBody RealtimeMonitoringAO request,
                                                  HttpServletResponse response) {
        if (request == null || request.getCategory() == null || request.getCategory().trim().length() == 0) {
            return buildError(response, "分类维度不能为空");
        }
        if (request.getPageNo() == null || request.getPageNo().intValue() <= 0) {
            return buildError(response, "页码必须大于0");
        }
        if (request.getPageSize() == null || request.getPageSize().intValue() <= 0 || request.getPageSize().intValue() > 200) {
            return buildError(response, "分页大小范围应在1-200");
        }
        return marketingIndicatorMonitoringService.realtimeMonitoring(request);
    }

    private Map<String, Object> buildError(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.put("message", message);
        return result;
    }

    private LocalDate parseDate(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.length() == 0) {
            return null;
        }
        try {
            return LocalDate.parse(trimmed);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
