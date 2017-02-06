package com.ringov.notekeeper.model.interfaces;

import com.ringov.notekeeper.StorageMap;
import com.ringov.notekeeper.presenter.android_relations_providers.SharedPreferencesProvider;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SettingsModelAccess extends BaseModelAccess {
    StorageMap.STORAGE_TYPE getCurrentStorageType(SharedPreferencesProvider storageSettingsProvider);
    void changeCurrentStorageType(StorageMap.STORAGE_TYPE type, SharedPreferencesProvider storageSettingsProvider);
}
