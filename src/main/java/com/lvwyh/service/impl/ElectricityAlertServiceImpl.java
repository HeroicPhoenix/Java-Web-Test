package com.lvwyh.service.impl;

import com.lvwyh.ao.ElectricityForecastAO;
import com.lvwyh.ao.HighPriceWarningAO;
import com.lvwyh.entity.ElectricityUsageRecord;
import com.lvwyh.entity.HighPriceWarningConfig;
import com.lvwyh.mapper.ElectricityAlertMapper;
import com.lvwyh.service.ElectricityAlertService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectricityAlertServiceImpl implements ElectricityAlertService {

    private final ElectricityAlertMapper electricityAlertMapper;

    public ElectricityAlertServiceImpl(ElectricityAlertMapper electricityAlertMapper) {
        this.electricityAlertMapper = electricityAlertMapper;
    }

    @Override
    public Map<String, Object> forecastElectricityCost(ElectricityForecastAO request) {
        List<ElectricityUsageRecord> records = electricityAlertMapper.selectUsageByUser(request.getUserId(), request.getMonths());
        Map<String, Object> result = new HashMap<String, Object>();
        if (records == null || records.isEmpty()) {
            result.put("success", Boolean.FALSE);
            result.put("message", "No usage records found");
            return result;
        }
        double totalCost = 0;
        double totalUsage = 0;
        for (ElectricityUsageRecord record : records) {
            totalCost += record.getCostAmount();
            totalUsage += record.getUsageKwh();
        }
        double averageCost = totalCost / records.size();
        double averageUsage = totalUsage / records.size();
        double growthFactor = 0;
        if (records.size() > 1) {
            double firstCost = records.get(0).getCostAmount();
            double lastCost = records.get(records.size() - 1).getCostAmount();
            if (firstCost > 0) {
                growthFactor = (lastCost - firstCost) / firstCost;
            }
        }
        double forecastCost = averageCost * (1 + growthFactor / 2);
        result.put("success", Boolean.TRUE);
        result.put("forecastCost", roundTwoDecimal(forecastCost));
        result.put("averageUsage", roundTwoDecimal(averageUsage));
        result.put("monthsConsidered", records.size());
        return result;
    }

    @Override
    public Map<String, Object> warnHighPriceUser(HighPriceWarningAO request) {
        Map<String, Object> result = new HashMap<String, Object>();
        HighPriceWarningConfig config = electricityAlertMapper.selectWarningConfig();
        if (config == null) {
            result.put("success", Boolean.FALSE);
            result.put("message", "Warning configuration missing");
            return result;
        }
        boolean warning = request.getForecastCost() != null && request.getForecastCost() >= config.getCostThreshold();
        result.put("success", Boolean.TRUE);
        result.put("warning", warning);
        result.put("threshold", config.getCostThreshold());
        return result;
    }

    private double roundTwoDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
