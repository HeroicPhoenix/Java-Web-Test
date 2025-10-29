package java.service;

import java.entity.DataAsset;
import java.dto.DataArchivePolicyRequest;
import java.dto.DataAssetCatalogRequest;
import java.dto.DataBackupPolicyRequest;
import java.dto.DataDeletionPolicyRequest;
import java.dto.DataStoragePolicyRequest;
import java.util.List;

public interface DataAssetOptimizationService {

    DataAsset createCatalogEntry(DataAssetCatalogRequest request);

    DataAsset configureStorage(DataStoragePolicyRequest request);

    DataAsset configureBackup(DataBackupPolicyRequest request);

    DataAsset configureArchive(DataArchivePolicyRequest request);

    DataAsset configureDeletion(DataDeletionPolicyRequest request);

    List<DataAsset> listAll();
}
