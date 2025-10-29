package java.service;

import java.dto.DataLifecycleRequest;
import java.entity.DataLifecycleRecord;

public interface DataLifecycleService {

    DataLifecycleRecord recordLifecycle(DataLifecycleRequest request);

    DataLifecycleRecord planShortTerm(DataLifecycleRequest request);

    DataLifecycleRecord planLongTerm(DataLifecycleRequest request);
}
