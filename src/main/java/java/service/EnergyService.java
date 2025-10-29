package java.service;

import java.dto.EnergyForecastRequest;
import java.entity.EnergyUsageForecast;

public interface EnergyService {

    EnergyUsageForecast forecast(EnergyForecastRequest request);

    EnergyUsageForecast warnHighPrice(EnergyForecastRequest request);
}
