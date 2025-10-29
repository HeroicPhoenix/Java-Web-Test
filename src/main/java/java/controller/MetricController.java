package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.MetricRequest;
import java.entity.MetricRecord;
import java.service.MetricService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService service;

    public MetricController(MetricService service) {
        this.service = service;
    }

    @Operation(summary = "查询分析指定历史时点的营销业务指标")
    @PostMapping("/history")
    public ResponseEntity<CommonResponse<MetricRecord>> history(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.recordHistoricalMetric(request)));
    }

    @Operation(summary = "业务指标趋势变化分析-日")
    @PostMapping("/trend/day")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> day(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.analyzeDailyTrend(request)));
    }

    @Operation(summary = "业务指标趋势变化分析-周")
    @PostMapping("/trend/week")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> week(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.analyzeWeeklyTrend(request)));
    }

    @Operation(summary = "业务指标趋势变化分析-月")
    @PostMapping("/trend/month")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> month(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.analyzeMonthlyTrend(request)));
    }

    @Operation(summary = "业务指标与历史数据的对比-同比")
    @PostMapping("/compare/yoy")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> yoy(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.compareYearOverYear(request)));
    }

    @Operation(summary = "业务指标与历史数据的对比-环比")
    @PostMapping("/compare/mom")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> mom(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.compareMonthOverMonth(request)));
    }

    @Operation(summary = "营销业务指标实时监控分析-指标分类维度")
    @PostMapping("/monitoring")
    public ResponseEntity<CommonResponse<java.util.List<MetricRecord>>> monitoring(@RequestBody MetricRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Metric code is required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.monitorRealTime(request)));
    }

    private boolean invalid(MetricRequest request) {
        return request == null || request.getMetricCode() == null || request.getMetricCode().trim().isEmpty();
    }
}
