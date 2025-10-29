package java.mapper;

import java.entity.DataAsset;
import java.util.List;
import java.util.Optional;

public interface DataAssetOptimizationMapper {

    DataAsset insert(DataAsset asset);

    Optional<DataAsset> findById(Long id);

    List<DataAsset> findAll();

    DataAsset update(DataAsset asset);
}
