package java.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.dto.EnergyForecastRequest;
import java.entity.EnergyUsageForecast;
import java.service.EnergyService;
import java.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService service;

    public EnergyController(EnergyService service) {
        this.service = service;
    }

    @Operation(summary = "未来电量电费数据预测")
    @PostMapping("/forecast")
    public ResponseEntity<CommonResponse<EnergyUsageForecast>> forecast(@RequestBody EnergyForecastRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Customer number, recent consumption and cost are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.forecast(request)));
    }

    @Operation(summary = "对电价过高用户预警")
    @PostMapping("/alert")
    public ResponseEntity<CommonResponse<EnergyUsageForecast>> alert(@RequestBody EnergyForecastRequest request) {
        if (invalid(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponse.failure("Customer number, recent consumption and cost are required"));
        }
        return ResponseEntity.ok(CommonResponse.success(service.warnHighPrice(request)));
    }

    private boolean invalid(EnergyForecastRequest request) {
        return request == null || request.getCustomerNo() == null || request.getCustomerNo().trim().isEmpty()
                || request.getRecentConsumption() == null || request.getRecentConsumption() <= 0
                || request.getRecentCost() == null || request.getRecentCost() <= 0;
    }
}
