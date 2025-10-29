package java.service.impl;

import java.dto.EnergyForecastRequest;
import java.entity.EnergyUsageForecast;
import java.exception.BusinessException;
import java.mapper.EnergyMapper;
import java.service.EnergyService;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class EnergyServiceImpl implements EnergyService {

    private final EnergyMapper mapper;

    public EnergyServiceImpl(EnergyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public EnergyUsageForecast forecast(EnergyForecastRequest request) {
        validate(request);
        return mapper.save(buildForecast(request, false));
    }

    @Override
    public EnergyUsageForecast warnHighPrice(EnergyForecastRequest request) {
        validate(request);
        double predictedCost = request.getRecentCost() * 1.15;
        boolean highRisk = predictedCost > request.getRecentCost() * 1.1;
        EnergyUsageForecast forecast = buildForecast(request, highRisk);
        forecast.setPredictedCost(predictedCost);
        return mapper.save(forecast);
    }

    private EnergyUsageForecast buildForecast(EnergyForecastRequest request, boolean highRisk) {
        EnergyUsageForecast forecast = new EnergyUsageForecast();
        forecast.setCustomerNo(request.getCustomerNo());
        LocalDate date = request.getForecastDate() != null ? request.getForecastDate() : LocalDate.now().plusDays(7);
        forecast.setForecastDate(date);
        forecast.setPredictedConsumption(request.getRecentConsumption() * 1.05);
        forecast.setPredictedCost(request.getRecentCost() * 1.05);
        forecast.setHighRisk(highRisk);
        return forecast;
    }

    private void validate(EnergyForecastRequest request) {
        if (request == null || request.getCustomerNo() == null || request.getCustomerNo().trim().isEmpty()) {
            throw new BusinessException("Customer number is required");
        }
        if (request.getRecentConsumption() == null || request.getRecentConsumption() <= 0) {
            throw new BusinessException("Recent consumption must be positive");
        }
        if (request.getRecentCost() == null || request.getRecentCost() <= 0) {
            throw new BusinessException("Recent cost must be positive");
        }
    }
}
