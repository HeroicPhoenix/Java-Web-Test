package java.service;

import java.dto.SlowSqlRequest;
import java.entity.SlowSqlRecord;

public interface SlowSqlService {

    SlowSqlRecord categorizeQuery(SlowSqlRequest request);

    SlowSqlRecord adjustStructure(SlowSqlRequest request);

    SlowSqlRecord createMonitoringRecord(SlowSqlRequest request);
}
