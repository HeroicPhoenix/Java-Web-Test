package java.mapper.impl;

import java.entity.DataAsset;
import java.mapper.DataAssetOptimizationMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class DataAssetOptimizationMapperImpl implements DataAssetOptimizationMapper {

    private final ConcurrentHashMap<Long, DataAsset> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public DataAsset insert(DataAsset asset) {
        long id = sequence.getAndIncrement();
        asset.setId(id);
        asset.setUpdatedAt(LocalDateTime.now());
        storage.put(id, asset);
        return asset;
    }

    @Override
    public Optional<DataAsset> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<DataAsset> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public DataAsset update(DataAsset asset) {
        asset.setUpdatedAt(LocalDateTime.now());
        storage.put(asset.getId(), asset);
        return asset;
    }
}
