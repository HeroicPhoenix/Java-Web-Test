package java.entity;

import java.time.LocalDate;

public class EnergyUsageForecast {

    private Long id;
    private String customerNo;
    private LocalDate forecastDate;
    private Double predictedConsumption;
    private Double predictedCost;
    private boolean highRisk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Double getPredictedConsumption() {
        return predictedConsumption;
    }

    public void setPredictedConsumption(Double predictedConsumption) {
        this.predictedConsumption = predictedConsumption;
    }

    public Double getPredictedCost() {
        return predictedCost;
    }

    public void setPredictedCost(Double predictedCost) {
        this.predictedCost = predictedCost;
    }

    public boolean isHighRisk() {
        return highRisk;
    }

    public void setHighRisk(boolean highRisk) {
        this.highRisk = highRisk;
    }
}
