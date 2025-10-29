package java.dto;

import java.time.LocalDate;

public class EnergyForecastRequest {

    private String customerNo;
    private Double recentConsumption;
    private Double recentCost;
    private LocalDate forecastDate;

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Double getRecentConsumption() {
        return recentConsumption;
    }

    public void setRecentConsumption(Double recentConsumption) {
        this.recentConsumption = recentConsumption;
    }

    public Double getRecentCost() {
        return recentCost;
    }

    public void setRecentCost(Double recentCost) {
        this.recentCost = recentCost;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }
}
