package java.mapper.impl;

import java.entity.EnergyUsageForecast;
import java.mapper.EnergyMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class EnergyMapperImpl implements EnergyMapper {

    private final ConcurrentHashMap<Long, EnergyUsageForecast> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public EnergyUsageForecast save(EnergyUsageForecast forecast) {
        if (forecast.getId() == null) {
            forecast.setId(sequence.getAndIncrement());
        }
        storage.put(forecast.getId(), forecast);
        return forecast;
    }

    @Override
    public List<EnergyUsageForecast> findByCustomer(String customerNo) {
        List<EnergyUsageForecast> result = new ArrayList<>();
        for (EnergyUsageForecast forecast : storage.values()) {
            if (forecast.getCustomerNo().equalsIgnoreCase(customerNo)) {
                result.add(forecast);
            }
        }
        return result;
    }
}
