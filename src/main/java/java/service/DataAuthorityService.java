package java.service;

import java.dto.DataAuthorityRequest;
import java.entity.DataAuthorityRecord;
import java.util.List;

public interface DataAuthorityService {

    DataAuthorityRecord createAuthority(DataAuthorityRequest request);

    List<DataAuthorityRecord> traceSource(DataAuthorityRequest request);
}
