package com.lvwyh.service;

import com.lvwyh.ao.ElectricityForecastAO;
import com.lvwyh.ao.HighPriceWarningAO;

import java.util.Map;

public interface ElectricityAlertService {
    Map<String, Object> forecastElectricityCost(ElectricityForecastAO request);

    Map<String, Object> warnHighPriceUser(HighPriceWarningAO request);
}
