package java.service;

import java.dto.RedundantDataRequest;
import java.entity.RedundantDataRecord;

public interface RedundantDataService {

    RedundantDataRecord exportRedundantData(RedundantDataRequest request);
}
