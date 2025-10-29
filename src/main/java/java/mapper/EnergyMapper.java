package java.mapper;

import java.entity.EnergyUsageForecast;
import java.util.List;

public interface EnergyMapper {

    EnergyUsageForecast save(EnergyUsageForecast forecast);

    List<EnergyUsageForecast> findByCustomer(String customerNo);
}
